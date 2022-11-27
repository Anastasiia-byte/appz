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
    @NotBlank
    private String photo;
    @NotBlank
    private String location;
    @Positive
    private int numberOfRooms;
    private boolean arranged;
    private boolean balcony;
    @NotBlank
    private String description;
    private LessorDTO lessor;
}
