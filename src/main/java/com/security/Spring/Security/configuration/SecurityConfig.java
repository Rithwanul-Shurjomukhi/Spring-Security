package com.security.Spring.Security.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
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

    private final RestAuthenticationEntryPoint authenticationEntryPoint;

    private final PasswordEncoder passwordEncoder;


    /*
    *  Swagger api's are not required authentication
    * */
    private final String[] swaggerUrl = {
            "/swagger-ui.html",
            "/swagger-ui/**",
            "/swagger-resources/**",
            "/swagger-resources",
            "/v3/api-docs/**",
            "/proxy/**"
    };

    @Autowired
    public SecurityConfig(RestAuthenticationEntryPoint authenticationEntryPoint, PasswordEncoder passwordEncoder) {
        this.authenticationEntryPoint = authenticationEntryPoint;
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public SecurityFilterChain publicSecurityFilter(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/api/public/**").permitAll()
                        .requestMatchers(swaggerUrl).permitAll()
                        .anyRequest().authenticated()
                ).httpBasic(httpBasic -> {
                    httpBasic.authenticationEntryPoint(authenticationEntryPoint);
                });

        /*
        *  CORS disabled
        * */
        http.cors(AbstractHttpConfigurer::disable);

        /*
        *  CSRF token disable
        * */
        http.csrf(AbstractHttpConfigurer::disable);

        return http.build();

    }


    /*
    *  Creating user in the memory
    * */
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User
                .withUsername("Dico")
                .password(passwordEncoder.encode("1111"))
                .roles("USER")
                .build();

        UserDetails admin = User.withUsername("Admin")
                .password(passwordEncoder.encode("1111"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user, admin);
    }

}
