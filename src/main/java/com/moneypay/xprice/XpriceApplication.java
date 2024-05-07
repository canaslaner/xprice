package com.moneypay.xprice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class XpriceApplication {

    public static void main(String[] args) {
        SpringApplication.run(XpriceApplication.class, args);
    }

}
