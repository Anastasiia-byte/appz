package com.example.appz.dtos;

import com.example.appz.entities.Lessor;
import lombok.Data;

import javax.persistence.ManyToOne;

@Data
public class DwellingDTO {
    private long id;
    private String name;
    private double price;
    private String description;
    private Lessor lessor;
}
