package com.moneypay.xprice.service;

import com.moneypay.xprice.controller.dto.request.AddProductPriceRequest;
import com.moneypay.xprice.model.ProductPrice;
import java.util.List;

public interface ProductPriceService {

    ProductPrice add(long productId, AddProductPriceRequest request);

    List<ProductPrice> getAscSortedPrices(long productId);
}
