package com.example.appz.dtos;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class MessageDTO {
    @Min(0)
    private long id;
    @NotNull
    private UserDTO user;
    @NotBlank
    private String text;
}
