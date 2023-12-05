package com.example.bookkeeping.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI api() {
        return new OpenAPI()
                .info(new Info().title("Микросервис по начислению стипендии и зарплаты")
                        .description("REST API счетов и операций по ним")
                        .version("1.0"));
    }
}
