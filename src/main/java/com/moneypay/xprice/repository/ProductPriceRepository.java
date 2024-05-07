package com.moneypay.xprice.repository;

import com.moneypay.xprice.model.ProductPrice;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductPriceRepository extends JpaRepository<ProductPrice, Long> {

    @EntityGraph(attributePaths = "product")
    Optional<ProductPrice> findByProductIdAndSeller(Long productId, String seller);

    List<ProductPrice> findByProduct_IdOrderByAmount_AmountAsc(long id);
}