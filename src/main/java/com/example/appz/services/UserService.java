package com.example.appz.services;

import com.example.appz.dtos.UserDTO;
import com.example.appz.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserDTO getById(final long id) {

    }

}
