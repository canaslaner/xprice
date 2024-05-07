package com.moneypay.xprice.controller.dto.request;

import com.moneypay.xprice.controller.validation.CreateProductValidationGroup;
import com.moneypay.xprice.controller.validation.UpdateProductValidationGroup;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

@Getter
@Setter
@Builder
public class ProductRequest {

    @NotBlank(message = "[brand] field should not be blank", groups = {CreateProductValidationGroup.class})
    private String brand;
    @NotBlank(message = "[name] field should not be blank", groups = {CreateProductValidationGroup.class})
    private String name;

    @AssertTrue(message = "at least one of [brand] or [name] fields should not be null", groups = {
            UpdateProductValidationGroup.class})
    private boolean isRequestValid() {
        return StringUtils.isNotBlank(this.brand) && StringUtils.isNotBlank(this.name);
    }
}
