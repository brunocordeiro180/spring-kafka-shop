package br.com.company.shopapi.model;

import br.com.company.shopapi.dto.ShopDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Entity(name = "shop")
public class Shop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String identifier;

    private String status;

    @Column(name = "buyer_identifier")
    private String buyerIdentifier;

    @Column(name = "date_shop")
    private LocalDate dateShop;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "shop")
    private List<ShopItem> items;

    public static Shop convert(ShopDTO shopDTO){
        Shop shop = new Shop();
        shop.setIdentifier(shopDTO.getIdentifier());
        shop.setStatus(shopDTO.getStatus());
        shop.setBuyerIdentifier(shopDTO.getBuyerIdentifier());
        shop.setDateShop(shopDTO.getDateShop());
        shop.setItems(shopDTO.getItems().stream().map(ShopItem::convert)
                .collect(Collectors.toList()));
        return shop;
    }
}
