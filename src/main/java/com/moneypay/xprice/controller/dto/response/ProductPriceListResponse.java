package com.moneypay.xprice.controller.dto.response;

import com.moneypay.xprice.controller.dto.base.BaseResponse;
import com.moneypay.xprice.dto.ProductPriceDto;
import java.io.Serial;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class ProductPriceListResponse extends BaseResponse {

    @Serial
    private static final long serialVersionUID = 1L;

    private List<ProductPriceDto> productPrices;
}
