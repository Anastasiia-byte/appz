package com.example.appz.dtos;

import com.example.appz.entities.Lessor;
import lombok.Data;

import javax.persistence.ManyToOne;
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
    @NotNull
    private Lessor lessor;
}
