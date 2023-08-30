package com.security.spring.controller;

import com.security.spring.config.JwtToken;
import com.security.spring.model.AuthenticationResponse;
import com.security.spring.model.UserDTO;
import com.security.spring.service.UserServiceServ;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class AuthenticationController {
    @Lazy
    @Autowired
    private AuthenticationManager manager;
    @Autowired
    private JwtToken token;
    @Autowired
    private UserServiceServ userservice;
    @Bean
    public AuthenticationManager manager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @PostMapping("/authentication")
    public AuthenticationResponse createToken(@RequestBody UserDTO userDTO, HttpServletResponse response) throws BadCredentialsException,
            DisabledException, UsernameNotFoundException, IOException {
        try {
            manager.authenticate(new UsernamePasswordAuthenticationToken(userDTO.getEmail(), userDTO.getPassword()));
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Incorrect username or password");
        } catch (DisabledException exception) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "User is not created");
            return null;
        }
        final UserDetails userDetails = userservice.loadUserByUsername(userDTO.getEmail());
        final String jwt = token.generateToken(userDetails.getUsername());
        return new AuthenticationResponse(jwt);
    }
}
