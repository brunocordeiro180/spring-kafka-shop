package br.com.company.shopapi.dto;

import br.com.company.shopapi.model.Shop;
import br.com.company.shopapi.model.ShopItem;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class ShopDTO {

    private String identifier;
    private String buyerIdentifier;
    private String status;
    private LocalDate dateShop;
    private List<ShopItemDTO> items;

    public static ShopDTO convert(Shop shop){
        ShopDTO shopDTO = new ShopDTO();
        shopDTO.setIdentifier(shop.getIdentifier());
        shopDTO.setStatus(shop.getStatus());
        shopDTO.setBuyerIdentifier(shop.getBuyerIdentifier());
        shopDTO.setDateShop(shop.getDateShop());
        shopDTO.setItems(shop.getItems().stream().map(ShopItemDTO::convert)
                .collect(Collectors.toList()));
        return shopDTO;
    }
}
