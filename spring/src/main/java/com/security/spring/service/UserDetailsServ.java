package com.security.spring.service;

import com.security.spring.model.Singup;
import com.security.spring.model.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserDetailsServ extends UserDetailsService {
    UserDTO createUser(Singup signup);
}
