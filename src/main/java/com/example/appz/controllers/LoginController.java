package com.example.appz.controllers;

import com.example.appz.dtos.LoginUserDTO;
import com.example.appz.dtos.UserDTO;
import com.example.appz.entities.CustomUserDetails;
import com.example.appz.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class LoginController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private UserDetailsService userDetailsService;

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/login")
    public void login(@RequestBody @Validated LoginUserDTO loginUserDTO) {
        CustomUserDetails userDetails = (CustomUserDetails) userDetailsService.loadUserByUsername(loginUserDTO.getEmail());
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUserDTO.getEmail(), loginUserDTO.getPassword(), userDetails.getAuthorities()));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/register")
    public UserDTO register(@RequestBody @Validated UserDTO userDTO) {
        return userService.create(userDTO);
    }
}
