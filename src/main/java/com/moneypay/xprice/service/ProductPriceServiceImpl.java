package com.moneypay.xprice.service;

import com.moneypay.xprice.constant.AppConstants;
import com.moneypay.xprice.controller.dto.request.AddProductPriceRequest;
import com.moneypay.xprice.model.ProductPrice;
import com.moneypay.xprice.model.base.Amount;
import com.moneypay.xprice.repository.ProductPriceRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductPriceServiceImpl implements ProductPriceService {

    private final ProductPriceRepository productPriceRepository;
    private final ProductService productService;

    @Override
    public ProductPrice add(final long productId, final AddProductPriceRequest request) {
        final var modifiedPrice = productPriceRepository.findByProductIdAndSeller(productId, request.getSeller())
                .orElseGet(() -> productService.findById(productId)
                        .map(product -> ProductPrice.builder()
                                .product(product)
                                .seller(request.getSeller())
                                .build())
                        .orElseThrow(() -> new EntityNotFoundException("Product not found")))
                .setAmount(Amount.of(request.getAmount(), AppConstants.Currency.TRY));

        return productPriceRepository.save(modifiedPrice);
    }

    @Override
    public List<ProductPrice> getAscSortedPrices(final long productId) {
        if (!productService.existsById(productId)) {
            throw new EntityNotFoundException("Product not found");
        }

        return productPriceRepository.findByProduct_IdOrderByAmount_AmountAsc(productId);
    }
}
