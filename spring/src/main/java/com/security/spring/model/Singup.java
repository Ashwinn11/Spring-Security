package com.security.spring.model;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
public class Singup {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
