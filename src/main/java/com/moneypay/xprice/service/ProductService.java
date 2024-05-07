package com.moneypay.xprice.service;

import com.moneypay.xprice.controller.dto.request.ProductRequest;
import com.moneypay.xprice.model.Product;
import java.util.Optional;

public interface ProductService {

    Optional<Product> findById(Long id);

    boolean existsById(Long id);

    Product create(ProductRequest request);

    Product update(Long id, ProductRequest request);
}
