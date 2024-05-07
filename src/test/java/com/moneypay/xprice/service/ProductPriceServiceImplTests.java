package com.moneypay.xprice.service;

import com.moneypay.xprice.controller.dto.request.AddProductPriceRequest;
import com.moneypay.xprice.model.Product;
import com.moneypay.xprice.model.ProductPrice;
import com.moneypay.xprice.model.base.Amount;
import com.moneypay.xprice.repository.ProductPriceRepository;
import jakarta.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ProductPriceServiceImplTests {

    @InjectMocks
    private ProductPriceServiceImpl productPriceService;
    @Mock
    private ProductPriceRepository productPriceRepository;
    @Mock
    private ProductService productService;

    @Test
    void testAdd_whenPriceExists_thenShouldUpdateAmount() {
        // given
        final long productId = 1;
        final var dto = AddProductPriceRequest.builder().amount(BigDecimal.TEN).seller("can").currency("TRY").build();
        final var price = ProductPrice.builder().id(1).amount(Amount.of(BigDecimal.ONE, "TRY"))
                .seller(dto.getSeller()).build();

        Mockito.doReturn(Optional.of(price)).when(productPriceRepository)
                .findByProductIdAndSeller(productId, dto.getSeller());
        Mockito.doReturn(price).when(productPriceRepository).save(price);

        // when
        final var returnedPrice = productPriceService.add(productId, dto);

        // then
        Assertions.assertEquals(dto.getAmount(), returnedPrice.getAmount().getAmount());
    }

    @Test
    void testAdd_whenPriceNotExistsAndProductExists_thenShouldCreate() {
        // given
        final long productId = 1;
        final Product product = Product.builder().id(productId).name("name").brand("brand").build();
        final var dto = AddProductPriceRequest.builder().amount(BigDecimal.TEN).seller("can").currency("TRY").build();

        Mockito.doReturn(Optional.empty()).when(productPriceRepository)
                .findByProductIdAndSeller(productId, dto.getSeller());
        Mockito.when(productPriceRepository.save(ArgumentMatchers.any())).thenAnswer(i -> i.getArguments()[0]);
        Mockito.doReturn(Optional.of(product)).when(productService).findById(productId);

        // when
        final var returnedPrice = productPriceService.add(productId, dto);

        // then
        Assertions.assertEquals(product, returnedPrice.getProduct());
        Assertions.assertEquals(dto.getSeller(), returnedPrice.getSeller());
        Assertions.assertEquals(dto.getAmount(), returnedPrice.getAmount().getAmount());
    }

    @Test
    void testAdd_whenPriceNotExistsAndProductNotExists_thenShouldThrowException() {
        // given
        final long productId = 1;
        final var dto = AddProductPriceRequest.builder().amount(BigDecimal.TEN).seller("can").currency("TRY").build();

        Mockito.doReturn(Optional.empty()).when(productPriceRepository)
                .findByProductIdAndSeller(productId, dto.getSeller());
        Mockito.doReturn(Optional.empty()).when(productService).findById(productId);

        // when
        final var exception = Assertions.assertThrows(EntityNotFoundException.class,
                () -> productPriceService.add(productId, dto));

        // then
        Assertions.assertEquals("Product not found", exception.getMessage());
    }
}
