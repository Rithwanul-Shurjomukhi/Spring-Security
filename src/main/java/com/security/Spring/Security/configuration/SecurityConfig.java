package com.security.Spring.Security.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


/*
*  This class holds the logic of application security
*  Here I have Used 3 filters for every request.
*  first one is public filter, second one is user filter,
*  third one is admin filter. Later use the in memory database
*  for the users.
* */


@Configuration
@EnableWebSecurity(debug = true)
public class SecurityConfig {

    private final String[] swaggerUrl = {
            "/swagger-ui.html",
            "/swagger-ui/**",
            "/swagger-resources/**",
            "/swagger-resources",
            "/v3/api-docs/**",
            "/proxy/**"
    };

    @Bean
    public SecurityFilterChain publicSecurityFilter(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests((authorize) -> authorize
                .requestMatchers("/api/public/**").permitAll()
                .requestMatchers(swaggerUrl).permitAll()
                .requestMatchers("/api/user/**").hasRole("USER")
                .anyRequest().authenticated()
        );
        http.exceptionHandling(ex -> ex.accessDeniedPage("/UnAuthorized"));

        return http.build();

    }


    @Bean
    public UserDetailsService userDetailsService() {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        UserDetails user = User
                .withUsername("Dico")
                .password(encoder.encode("1111"))
                .roles("USER")
                .build();

        UserDetails admin = User.withUsername("Admin")
                .password(encoder.encode("1111"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user, admin);
    }

}
