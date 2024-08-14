package com.security.Spring.Security.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class ApiDocumentation {

    @Value("${persistence.openapi.prod-url}")
    private String productionUrl;

    @Value("${persistence.openapi.dev-url}")
    private String devUrl;

    @Bean
    public OpenAPI openApi() {
        Server devServer = new Server()
                .url(devUrl)
                .description("Server Url in development environment");

        Server productionServer = new Server()
                .url(productionUrl)
                .description("Server Url in production environment");

        Contact contact = new Contact()
                .url("rithwanul@gmail.com")
                .email("rithwanul@gmail.com")
                .name("Rithwanul Islam");

        License license = new License()
                .url("https://www.rithwanul.com")
                .name("Daga Daga");

        Info info = new Info()
                .title("Hibernate Tutorial")
                .description("Documentation for rest endpoints")
                .contact(contact)
                .license(license)
                .version("1.0.0");



        List<Server> servers = Arrays.asList(devServer, productionServer);

        return new OpenAPI().info(info).servers(servers);

    }
}