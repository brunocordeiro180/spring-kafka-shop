package br.com.company.shopreport.dto;

import br.com.company.shopreport.model.ShopReport;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShopReportDTO {
    private String identifier;
    private Integer amount;

    public static ShopReportDTO convert(ShopReport shopReport){
        ShopReportDTO shopReportDTO = new ShopReportDTO();
        shopReportDTO.setIdentifier(shopReport.getIdentifier());
        shopReportDTO.setAmount(shopReport.getAmount());
        return shopReportDTO;
    }
}
