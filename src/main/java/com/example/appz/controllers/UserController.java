package com.example.appz.controllers;

import com.example.appz.dtos.UserDTO;
import com.example.appz.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/api/user")
@Validated
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService userService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public UserDTO getById(@PathVariable @Min(0) long id) {
        log.info("Received request to get user by id " + id);
        return userService.getById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<UserDTO> getAll() {
        log.info("Received request to get all users");
        return userService.getAll();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/consultants")
    public List<UserDTO> getAllConsultants() {
        log.info("Received request to get all admins");
        return userService.getAllConsultants();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public UserDTO create(@Valid @RequestBody UserDTO userDTO) {
        log.info("Received request to create a new user with email " + userDTO.getEmail());
        return userService.create(userDTO);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping
    public UserDTO update(@Valid @RequestBody UserDTO userDTO) {
        log.info("Received request to update a user with id " + userDTO.getId());
        return userService.update(userDTO);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable @Min(0) long id) {
        log.info("Received request to delete a user with id " + id);
        userService.delete(id);
    }
}
