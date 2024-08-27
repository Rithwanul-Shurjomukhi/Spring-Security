package com.security.Spring.Security.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {

    @GetMapping("/public")
    public ResponseEntity<?> publicUser() {
        return new ResponseEntity<String>("Hi public user", HttpStatus.OK);
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> generalUser() {
        return new ResponseEntity<String>("Hi general user", HttpStatus.OK);
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?>  adminEndpoint() {
        return new ResponseEntity<String>("Hi admin user", HttpStatus.OK);

    }
}
