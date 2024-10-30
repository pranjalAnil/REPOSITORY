package com.example.Project.services.impl;

import com.example.Project.entities.User;
import com.example.Project.exception.ResourceNotFoundException;
import com.example.Project.repositories.UserRepo;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

@Service
public class SecurityCustomDetailService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByEmail(username)
                .orElseThrow(
                        () -> new ResourceNotFoundException("User", "Username " + username, 0
                        ));
    }
}
