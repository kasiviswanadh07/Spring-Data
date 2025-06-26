package com.abhishekvermaa10.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI petistaanOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Petistaan API")
                        .description("API documentation for Petistaan - A pet management system.")
                        .version("1.0")
                        .contact(new Contact()
                                .name("Kasi viswanadh Rapeti ")
                                .email("viswanadhkasirapeti77@gmail.com")  
                                ));
    }
}
