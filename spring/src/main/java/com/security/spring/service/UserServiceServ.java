package com.security.spring.service;
import com.security.spring.model.Singup;
import com.security.spring.model.User;
import com.security.spring.model.UserDTO;
import com.security.spring.repository.Userrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserServiceServ implements UserDetailsServ {
    @Autowired
    private Userrepository userrepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = (User) userrepository.findByEmail(email);
        if ( user == null) throw new UsernameNotFoundException("User not found", null);
        return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),new ArrayList<>());
    }
    @Override
    public UserDTO createUser(Singup signup) {
        User user = new User();
        user.setEmail(signup.getEmail());
        user.setFirstName(signup.getFirstName());
        user.setLastName(signup.getLastName());
        user.setEmail(signup.getEmail());
        user.setPassword(new BCryptPasswordEncoder().encode(signup.getPassword()));
        userrepository.save(user);
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(user.getUserId());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        return userDTO;
    }
}
