package io.devandre.furtmates.shared.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Furtmates API")
                        .version("1.0")
                        .description("API documentation for Furtmates application")
                        .contact(new Contact()
                                .name("Andre Gallegos")
                                .email("andregallegos95@gmail.co")
                        )
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://springdoc.org")))
                .externalDocs(new ExternalDocumentation()
                        .description("Furtmates Wiki")
                        .url("https://wiki.furtmates.com"));
    }
}
