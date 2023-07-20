package com.dansmultipro.test.jobwebservices.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI openApi() {
        Contact contact = new Contact();
        contact.setEmail("example@email.com");
        contact.setName("Enginnering Dans Multi Pro");
        contact.setUrl("https://dansmultipro.com/");

        License mitLicense = new License().name("MIT License").url("https://choosealicense.com/licenses/mit/");
        Info info = new Info()
                .title("Jobs Web Services")
                .version("1.0")
                .contact(contact)
                .description("This API exposes endpoints to manage all requirements jobs.")
                .license(mitLicense);

        return new OpenAPI().info(info);
    }

}
