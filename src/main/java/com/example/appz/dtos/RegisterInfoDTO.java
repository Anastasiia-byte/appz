package com.example.appz.dtos;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class RegisterInfoDTO {
    @NotBlank
    @Email
    private String email;
    @NotNull
    @Min(1)
    private Integer numberOfRooms;
    private boolean balcony;
    private boolean arranged;
}
