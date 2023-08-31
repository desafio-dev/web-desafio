package br.com.bycoders.desafiodev.bankingservice.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfig {

    @Bean
    public OpenAPI apiDocConfig() {
        return new OpenAPI()
                .info(new Info()
                        .title("ByCoders_ Dev's Challenge")
                        .description("This page is to document the api restful :) ")
                        .version("0.0.1")
                        .contact(new Contact()
                                .name("LinkedIn")
                                .url("https://linkedin.com/in/salomao123")))
                .externalDocs(new ExternalDocumentation()
                        .description("Github Documentations")
                        .url("https://github.com/Salomao123"));
    }
}