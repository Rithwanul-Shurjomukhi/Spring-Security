package com.user.security.controller;


import com.user.security.entity.User;
import com.user.security.model.UserModel;
import com.user.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/public/user")
public class RegistrationController {

    @Autowired
    private UserService userService;


    @GetMapping("/registration")
    public ResponseEntity<?> registerUser(@RequestBody UserModel userModel) {
        User user = userService.registerUser(userModel);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
