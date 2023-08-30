package com.security.spring.repository;

import com.security.spring.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface Userrepository extends JpaRepository<User,Integer> {
    UserDetails findByEmail(String email);
}
