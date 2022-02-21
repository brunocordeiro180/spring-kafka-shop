package br.com.company.shopreport.controller;

import br.com.company.shopreport.dto.ShopReportDTO;
import br.com.company.shopreport.repository.ShopReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/shop_report")
@RequiredArgsConstructor
public class ShopController {

    private final ShopReportRepository shopReportRepository;

    @GetMapping
    public List<ShopReportDTO> getShopReport(){
        return shopReportRepository
                .findAll()
                .stream()
                .map(ShopReportDTO::convert)
                .collect(Collectors.toList());
    }
}
