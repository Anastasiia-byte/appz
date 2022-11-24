package com.example.appz.dtos;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class LoginUserDTO {
    @Email
    private String email;
    @NotBlank
    private String password;
}
