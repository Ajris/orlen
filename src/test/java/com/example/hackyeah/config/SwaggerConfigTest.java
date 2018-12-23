package com.example.hackyeah.config;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

public class SwaggerConfigTest {
    @Test
    public void shouldReturnNewSwaggerConfigDocket() {
        SwaggerConfig swaggerConfig = new SwaggerConfig();

        Docket docket = swaggerConfig.api();

        Assertions.assertEquals(docket.getDocumentationType(), DocumentationType.SWAGGER_2);
    }
}
