package com.moneypay.xprice.controller.dto.request;

import com.moneypay.xprice.constant.AppConstants;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import java.math.BigDecimal;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AddProductPriceRequest {

    @NotBlank(message = "[seller] field should not be blank")
    private String seller;
    @Min(value = 0, message = "[amount] field should not be blank")
    private BigDecimal amount;
    @NotBlank(message = "[currency] field should not be blank")
    private String currency;

    @AssertTrue(message = "unsupported [currency], we only support TRY for now")
    private boolean isCurrencyValid() {
        return AppConstants.Currency.TRY.equals(currency);
    }
}
