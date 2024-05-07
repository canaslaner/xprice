package com.moneypay.xprice.controller;

import com.moneypay.xprice.controller.dto.base.Result;
import com.moneypay.xprice.controller.dto.request.AddProductPriceRequest;
import com.moneypay.xprice.controller.dto.response.AddProductPriceResponse;
import com.moneypay.xprice.controller.dto.response.ProductPriceListResponse;
import com.moneypay.xprice.dto.ProductDto;
import com.moneypay.xprice.dto.ProductPriceDto;
import com.moneypay.xprice.service.ProductPriceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/v0/product/{productId}/price")
@RequiredArgsConstructor
@Tag(name = "Product Price Api", description = "Product price related processes")
public class ProductPriceController {

    private final ProductPriceService productPriceService;

    @PostMapping
    @Operation(summary = "add", description = "Product price create/update endpoint")
    public AddProductPriceResponse add(@Valid @Positive @PathVariable("productId") Long productId,
            @Valid @RequestBody final AddProductPriceRequest request) {
        final var createdPrice = productPriceService.add(productId, request);
        return AddProductPriceResponse.builder()
                .result(Result.success())
                .product(ProductDto.from(createdPrice.getProduct()))
                .productPrice(ProductPriceDto.from(createdPrice))
                .build();
    }

    @PostMapping(value = "/list")
    @Operation(summary = "list", description = "All product prices as sorted endpoint")
    public ProductPriceListResponse getPrices(@Valid @Positive @PathVariable("productId") Long productId) {
        final var sortedPrices = productPriceService.getAscSortedPrices(productId);
        return ProductPriceListResponse.builder()
                .result(Result.success())
                .productPrices(sortedPrices
                        .stream()
                        .map(ProductPriceDto::from)
                        .toList())
                .build();
    }

}
