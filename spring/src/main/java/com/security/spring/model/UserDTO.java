package com.security.spring.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor

public class UserDTO {
    private Integer userId;
    private String email;
    private String password;
}