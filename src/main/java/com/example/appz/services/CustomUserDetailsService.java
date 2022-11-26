package com.example.appz.services;

import com.example.appz.entities.CustomUserDetails;
import com.example.appz.entities.User;
import com.example.appz.exceptions.EntityNotFoundException;
import com.example.appz.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> userMaybe = userRepository.findByEmail(email);

        return userMaybe
                .map(CustomUserDetails::new)
                .orElseThrow(() -> new EntityNotFoundException("User with email " + email + " was not found"));
    }
}
