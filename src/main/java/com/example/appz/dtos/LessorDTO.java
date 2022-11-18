package com.example.appz.dtos;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class LessorDTO {
    @Min(0)
    private long id;
    @NotBlank
    @Size(min = 2)
    private String name;
    @NotBlank
    @Size(min = 2)
    private String surname;
    @Email
    private String email;
}
