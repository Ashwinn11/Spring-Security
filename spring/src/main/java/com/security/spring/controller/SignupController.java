package com.security.spring.controller;
import com.security.spring.model.Singup;
import com.security.spring.model.UserDTO;
import com.security.spring.service.UserServiceServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SignupController {

    @Autowired
    private UserServiceServ userservice;
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Singup signup) {
        UserDTO created = userservice.createUser(signup);
        if (created == null) {
            return new ResponseEntity<>("User not created", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }


}


