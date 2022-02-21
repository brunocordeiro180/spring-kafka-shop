package br.com.company.shopvalidator.events;

import br.com.company.shopvalidator.dto.ShopDTO;
import br.com.company.shopvalidator.dto.ShopItemDTO;
import br.com.company.shopvalidator.model.Product;
import br.com.company.shopvalidator.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReceiveKafkaMessage {
    public static final String SHOP_TOPIC_NAME = "SHOP_TOPIC";
    private static final String SHOP_TOPIC_EVENT_NAME = "SHOP_TOPIC_EVENT";

    private final ProductRepository productRepository;
    private final KafkaTemplate<String, ShopDTO> kafkaTemplate;

    @KafkaListener(topics = SHOP_TOPIC_NAME, groupId = "group")
    public void listenShopTopic(ShopDTO shopDTO,
                                @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) String key,
                                @Header(KafkaHeaders.RECEIVED_PARTITION_ID) String partitionId,
                                @Header(KafkaHeaders.RECEIVED_TIMESTAMP) String timestamp){
        try{
            log.info("Compra recebida no tópico: {} com chave {} na particao {} na hora {}"
                    , shopDTO.getIdentifier(),
                    key, partitionId, timestamp);
            boolean success = true;
            for(ShopItemDTO shopItemDTO : shopDTO.getItems()){
                Product product = productRepository.findByIdentifier(shopItemDTO.getProductIdentifier());
                if(!isValidShop(shopItemDTO, product)){
                    shopError(shopDTO);
                    success = false;
                    break;
                }
            }

            if(success){
                shopSuccess(shopDTO);
            }
        }catch (Exception e){
            log.error("Erro no processamento da compra {}", shopDTO.getIdentifier());
        }
    }

    private boolean isValidShop(ShopItemDTO shopItemDTO, Product product){
        return product != null && product.getAmount() >= shopItemDTO.getAmount();
    }

    private void shopError(ShopDTO shopDTO){
        log.info("Erro no processamento da compra {}", shopDTO.getIdentifier());
        shopDTO.setStatus("ERROR");
        kafkaTemplate.send(SHOP_TOPIC_EVENT_NAME, shopDTO);
    }

    private void shopSuccess(ShopDTO shopDTO){
//        log.info("Compra {} efetuada com sucesso!", shopDTO.getIdentifier());
        shopDTO.setStatus("SUCCESS");
        kafkaTemplate.send(SHOP_TOPIC_EVENT_NAME, shopDTO);
    }
}
