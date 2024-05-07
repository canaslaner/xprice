package com.moneypay.xprice.controller.dto.base;

import java.io.Serial;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor(staticName = "instance")
public class Result implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String code;
    private String message;

    public static Result success() {
        return instance("0", "Success");
    }

    public static Result failure() {
        return instance("-1", "Failure");
    }
}