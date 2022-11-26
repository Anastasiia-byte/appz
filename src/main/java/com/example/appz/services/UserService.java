package com.example.appz.services;

import com.example.appz.dtos.UserDTO;
import com.example.appz.dtos.mappers.UserMapper;
import com.example.appz.entities.Role;
import com.example.appz.entities.User;
import com.example.appz.exceptions.EntityNotFoundException;
import com.example.appz.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Slf4j
@Service
public class UserService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    public UserDTO getById(final long id) {
        log.info("Retrieving a user with id " + id);
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User with id " + id + " was not found"));
        return userMapper.map(user);
    }

    public List<UserDTO> getAll() {
        log.info("Retrieving all users");
        return userMapper.map(userRepository.findAll());
    }

    public List<UserDTO> getAllConsultants() {
        log.info("Retrieving all consultants");
        return userMapper.map(userRepository.getAllConsultants());
    }

    public UserDTO create(UserDTO userDTO) {
        log.info("Creating a new user with email" + userDTO.getEmail());
        User user = userMapper.mapUserDTO(userDTO);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Collections.singleton(Role.USER));
        return userMapper.map(userRepository.save(user));
    }

    @Transactional
    public UserDTO update(UserDTO userDTO) {
        log.info("Updating a user with id " + userDTO.getId());
        User user = userMapper.mapUserDTO(userDTO);
        return userMapper.map(userRepository.save(user));
    }

    public void delete(long id) {
        log.info("Deleting a user with id " + id);
        userRepository.deleteById(id);
    }
}
