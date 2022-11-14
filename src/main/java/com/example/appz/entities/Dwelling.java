package com.example.appz.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "dwellings")
public class Dwelling {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private double price;
    private String description;
    @ManyToOne
    private Lessor lessor;
}
