package com.example.appz.dtos;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class DwellingDTO {
    @Min(0)
    private long id;
    @NotBlank
    @Size(min = 2)
    private String name;
    @Positive
    private double price;
    @NotBlank
    private String description;
}
