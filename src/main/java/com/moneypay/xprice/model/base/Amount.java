package com.moneypay.xprice.model.base;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Embeddable
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
@Getter
@Setter
public class Amount implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "currency")
    private String currency;
}