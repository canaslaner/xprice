package com.moneypay.xprice.controller;

import com.moneypay.xprice.MvcUtils;
import com.moneypay.xprice.controller.dto.request.ProductRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
class ProductControllerIntegrationTests {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @ParameterizedTest
    @CsvSource({
            ", name, [brand] field should not be blank",
            "brand, , [name] field should not be blank"
    })
    void testCreateProduct_whenRequestNotValid_thenShouldReturnBadRequest(final String brand, final String name,
            final String expectedMessage) throws Exception {
        // given
        final ProductRequest request = ProductRequest.builder().brand(brand).name(name).build();

        // when & then
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/v0/product")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .content(MvcUtils.createJsonContent(request)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(
                        MockMvcResultMatchers.jsonPath("$.result.message").value(expectedMessage));
    }

    @Test
    void testCreateProduct_whenRequestValid_thenShouldCreateAndReturnProduct() throws Exception {
        // given
        final ProductRequest request = ProductRequest.builder().brand("brand").name("name").build();

        // when & then
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/v0/product")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .content(MvcUtils.createJsonContent(request)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(
                        MockMvcResultMatchers.jsonPath("$.product.name").value("name"))
                .andExpect(
                        MockMvcResultMatchers.jsonPath("$.product.brand").value("brand"));
    }
}
