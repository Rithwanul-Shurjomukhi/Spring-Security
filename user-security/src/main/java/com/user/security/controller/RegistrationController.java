package com.user.security.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/public")
public class RegistrationController {

    @GetMapping("/name")
    public ResponseEntity<?> getUserName() {
        return new ResponseEntity<String>("Hi! I am Rithwan", HttpStatus.OK);
    }
}
