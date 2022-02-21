package br.com.company.shopapi.repository;

import br.com.company.shopapi.model.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopRepository extends JpaRepository<Shop, Long> {

    Shop findByIdentifier(String identifier);
}
