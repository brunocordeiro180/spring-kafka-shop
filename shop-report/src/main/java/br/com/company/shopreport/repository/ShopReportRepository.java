package br.com.company.shopreport.repository;

import br.com.company.shopreport.model.ShopReport;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

@ReadingConverter
public interface ShopReportRepository extends JpaRepository<ShopReport, Long> {

    @Modifying
    @Query(value = "update shop_report " +
            "set amount = amount + 1 " +
            "where identifier = :shopStatus",
            nativeQuery = true)
    void incrementShopStatus(String shopStatus);
}
