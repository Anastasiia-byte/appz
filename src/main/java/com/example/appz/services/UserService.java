package com.example.appz.services;

import com.example.appz.dtos.UserDTO;
import com.example.appz.dtos.mappers.UserMapper;
import com.example.appz.entities.Role;
import com.example.appz.entities.User;
import com.example.appz.exceptions.EmailAlreadyExistsException;
import com.example.appz.exceptions.EntityNotFoundException;
import com.example.appz.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

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

    public UserDTO getByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User with email " + email + " was not found"));
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

    public List<Long> getAllConsultantIds() {
        log.info("Retrieving all consultant ids");
        return userRepository.getAllConsultantIds();
    }

    public String getUserLocationByEmail(String email) {
        log.info("Retrieving location for user with email " + email);
        return userRepository.getUserLocationByEmail(email);
    }

    public UserDTO create(UserDTO userDTO) {
        log.info("Creating a new user with email" + userDTO.getEmail());
        User user = userMapper.mapUserDTO(userDTO);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Collections.singleton(Role.USER));
        return userMapper.map(userRepository.save(user));
    }

    public UserDTO createConsultant(UserDTO userDTO) {
        String email = userDTO.getEmail();
        log.info("Creating a new consultant with email" + email);

        if (checkEmailUnique(email)) {
            throw new EmailAlreadyExistsException("User with email " + email + " already exists");
        }

        User user = userMapper.mapUserDTO(userDTO);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Collections.singleton(Role.CONSULTANT));
        return userMapper.map(userRepository.save(user));
    }

    @Transactional
    public UserDTO update(UserDTO userDTO) {
        long id = userDTO.getId();
        log.info("Updating a user with id " + id);

        String email = userDTO.getEmail();
        if (checkEmailUnique(email)) {
            throw new EmailAlreadyExistsException("User with email " + email + " already exists");
        }

        userRepository.update(userDTO.getName(), userDTO.getSurname(), userDTO.getBirthDate(), userDTO.getLocation(), id);
        return userMapper.map(userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Use with id " + id + " was not found")));
    }

    public void delete(long id) {
        log.info("Deleting a user with id " + id);
        userRepository.deleteById(id);
    }

    private boolean checkEmailUnique(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        return user.isPresent();
    }
}
