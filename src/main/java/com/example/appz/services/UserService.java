package com.example.appz.services;

import com.example.appz.dtos.UserDTO;
import com.example.appz.dtos.mappers.UserMapper;
import com.example.appz.entities.User;
import com.example.appz.exceptions.EntityNotFoundException;
import com.example.appz.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class UserService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    public UserDTO getById(final long id) {
        log.info("Retrieving a user with id " + id);
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User with id " + id + " was not found"));
        return UserMapper.INSTANCE.map(user);
    }

    public List<UserDTO> getAll() {
        log.info("Retrieving all users");
        return UserMapper.INSTANCE.map(userRepository.findAll());
    }

    public UserDTO create(UserDTO userDTO) {
        log.info("Creating a new user with email" + userDTO.getEmail());
        User user = UserMapper.INSTANCE.mapUserDTO(userDTO);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return UserMapper.INSTANCE.map(userRepository.save(user));
    }

    @Transactional
    public UserDTO update(UserDTO userDTO) {
        log.info("Updating a user with id " + userDTO.getId());
        User user = UserMapper.INSTANCE.mapUserDTO(userDTO);
        return UserMapper.INSTANCE.map(userRepository.save(user));
    }

    public void delete(long id) {
        log.info("Deleting a user with id " + id);
        userRepository.deleteById(id);
    }
}
