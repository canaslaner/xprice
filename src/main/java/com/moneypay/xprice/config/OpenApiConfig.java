package com.moneypay.xprice.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(title = "Xprice Rest Api", version = "1.0.0", description = "Xprice project api documentation",
        contact = @Contact(name = "Name Surname")))
public class OpenApiConfig {

    //@Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new io.swagger.v3.oas.models.info.Info()
                        .title("Product API başlık")
                        .version("1.0")
                        .description("Product API açıklama")
                        .termsOfService("http://swagger.io/terms/")
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://springdoc.org")
                        )
                        .contact(new io.swagger.v3.oas.models.info.Contact()
                                .email("asd@gmail.com")
                                .name("Geliştirici")
                                .url("https://asd.com")
                        )
                );
    }
}
