package com.moneypay.xprice.controller.dto.request;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

@Getter
@Setter
@Builder
public class UpdateProductRequest {

    @NotNull(message = "id field should not be null")
    private Long id;
    private String brand;
    private String name;


    @AssertTrue(message = "at least one of [brand] or [name] fields should not be null")
    private boolean isRequestValid() {
        return StringUtils.isNotBlank(this.brand) && StringUtils.isNotBlank(this.name);
    }
}
