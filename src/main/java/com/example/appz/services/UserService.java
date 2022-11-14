package com.example.appz.services;

import com.example.appz.dtos.UserDTO;
import com.example.appz.dtos.mappers.UserMapper;
import com.example.appz.entities.User;
import com.example.appz.exceptions.EntityNotFoundException;
import com.example.appz.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserDTO getById(final long id) {
        log.info("Retr");
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User with id " + id + " was not found"));
        return UserMapper.INSTANCE.mapUser(user);
    }

    public List<UserDTO> getAll() {
        return UserMapper.INSTANCE.mapUserList(userRepository.findAll());
    }

}
