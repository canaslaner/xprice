package com.moneypay.xprice.repository;

import com.moneypay.xprice.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    boolean existsByBrandAndName(String brand, String name);

    boolean existsById(long id);
}