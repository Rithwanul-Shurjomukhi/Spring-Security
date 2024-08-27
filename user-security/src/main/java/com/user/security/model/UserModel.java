package com.user.security.model;


import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class UserModel {

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private String role;

    private boolean enabled = false;
}
