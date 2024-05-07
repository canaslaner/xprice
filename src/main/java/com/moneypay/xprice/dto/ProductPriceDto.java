package com.moneypay.xprice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.moneypay.xprice.model.ProductPrice;
import com.moneypay.xprice.model.base.Amount;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ProductPriceDto(String seller, Amount amount) {

    public static ProductPriceDto from(final ProductPrice productPrice) {
        return new ProductPriceDto(productPrice.getSeller(), productPrice.getAmount());
    }
}
