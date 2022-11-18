package com.example.appz.dtos;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class MessageDTO {
    @Min(0)
    private long id;
    @NotNull
    private UserDTO user;
    @NotBlank
    private String text;
}
