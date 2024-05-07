package com.moneypay.xprice.dto;

import com.moneypay.xprice.model.Product;

public record ProductDto(Long id, String brand, String name) {

    public static ProductDto from(final Product product) {
        return new ProductDto(product.getId(), product.getBrand(), product.getName());
    }
}
