package com.security.Spring.Security.configuration;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.security.SecurityRequirement;

import java.util.Arrays;
import java.util.List;

@Configuration
public class ApiDocumentation {

    @Value("${persistence.openapi.prod-url}")
    private String productionUrl;

    @Value("${persistence.openapi.dev-url}")
    private String devUrl;

    private final String securitySchemeName = "basicAuth";

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

        SecurityScheme scheme = new SecurityScheme()
                .name(securitySchemeName)
                .type(SecurityScheme.Type.HTTP).scheme("basic");

        Components components = new Components()
                .addSecuritySchemes(securitySchemeName, scheme);

        SecurityRequirement requirement = new SecurityRequirement().addList(securitySchemeName);

        List<Server> servers = Arrays.asList(devServer, productionServer);

        return new OpenAPI().info(info)
                .addSecurityItem(requirement)
                .servers(servers).components(components);

    }
}