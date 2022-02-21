package br.com.company.shopapi.dto;

import br.com.company.shopapi.model.Shop;
import br.com.company.shopapi.model.ShopItem;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
public class ShopItemDTO {

    private String productIdentifier;
    private Integer amount;
    private Float price;

    public static ShopItemDTO convert(ShopItem shopItem){
        ShopItemDTO shopItemDTO = new ShopItemDTO();
        shopItemDTO.setProductIdentifier(shopItem.getProductIdentifier());
        shopItemDTO.setAmount(shopItem.getAmount());
        shopItemDTO.setPrice(shopItem.getPrice());
        return shopItemDTO;
    }
}
