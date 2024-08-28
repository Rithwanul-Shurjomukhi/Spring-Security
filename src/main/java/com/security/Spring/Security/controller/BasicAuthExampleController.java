package com.security.Spring.Security.controller;


import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/public")
//@SecurityRequirement(name = "basicAuth")
public class BasicAuthExampleController {

    @GetMapping("/secure-api")
    public ResponseEntity<?> secureApi(@RequestHeader HttpHeaders headers) {
        if (headers.containsKey(HttpHeaders.AUTHORIZATION)) {
            String authorizationHeaders = headers.getFirst(HttpHeaders.AUTHORIZATION);
            if (authorizationHeaders != null) {
                if (authorizationHeaders.startsWith("Basic ")) {
                    return new ResponseEntity<>("Authorization Passed", HttpStatus.OK);
                }
            }

        }
        return new ResponseEntity<>("Unauthorized", HttpStatus.UNAUTHORIZED);
    }
}
