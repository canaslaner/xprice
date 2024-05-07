package com.moneypay.xprice.service;

import com.moneypay.xprice.controller.dto.request.ProductRequest;
import com.moneypay.xprice.model.Product;
import com.moneypay.xprice.repository.ProductRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public Optional<Product> findById(final Long id) {
        return productRepository.findById(id);
    }

    @Override
    public boolean existsById(final Long id) {
        return productRepository.existsById(id);
    }

    @Override
    public Product create(final ProductRequest request) {
        if (productRepository.existsByBrandAndName(request.getBrand(), request.getName())) {
            throw new EntityExistsException("Product already exists");
        }

        return productRepository.save(Product.builder()
                .brand(request.getBrand())
                .name(request.getName())
                .build());
    }

    @Override
    public Product update(final Long id, final ProductRequest request) {
        final var modifiedProduct = productRepository.findById(id)
                .map(product -> product
                        .setName(StringUtils.defaultIfBlank(request.getName(), product.getName()))
                        .setBrand(StringUtils.defaultIfBlank(request.getBrand(), product.getBrand())))
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));

        return productRepository.save(modifiedProduct);
    }
}
