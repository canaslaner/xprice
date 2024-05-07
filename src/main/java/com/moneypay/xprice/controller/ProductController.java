package com.moneypay.xprice.controller;

import com.moneypay.xprice.controller.dto.base.Result;
import com.moneypay.xprice.controller.dto.request.ProductRequest;
import com.moneypay.xprice.controller.dto.response.BaseProductResponse;
import com.moneypay.xprice.controller.validation.CreateProductValidationGroup;
import com.moneypay.xprice.controller.validation.UpdateProductValidationGroup;
import com.moneypay.xprice.dto.ProductDto;
import com.moneypay.xprice.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequestMapping(value = "/v0/product")
@RequiredArgsConstructor
@Tag(name = "Product Api", description = "Product related processes")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    @Operation(summary = "create", description = "Product creation endpoint")
    public BaseProductResponse create(
            @Validated(CreateProductValidationGroup.class) @RequestBody final ProductRequest request) {
        final var createdProduct = productService.create(request);
        return BaseProductResponse.builder()
                .result(Result.success())
                .product(ProductDto.from(createdProduct))
                .build();
    }

    @PutMapping(value = "/{productId}")
    @Operation(summary = "update", description = "Product modification endpoint")
    public BaseProductResponse update(@Valid @Positive @PathVariable("productId") Long productId,
            @Validated(UpdateProductValidationGroup.class) @RequestBody final ProductRequest request) {
        final var modifiedProduct = productService.update(productId, request);
        return BaseProductResponse.builder()
                .result(Result.success())
                .product(ProductDto.from(modifiedProduct))
                .build();
    }
}