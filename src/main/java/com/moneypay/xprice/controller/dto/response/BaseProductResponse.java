package com.moneypay.xprice.controller.dto.response;

import com.moneypay.xprice.controller.dto.base.BaseResponse;
import com.moneypay.xprice.dto.ProductDto;
import java.io.Serial;
import java.io.Serializable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class BaseProductResponse extends BaseResponse implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private ProductDto product;
}
