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
    private String description;
    private String photo;
    private String location;
    private int numberOfRooms;
    private boolean arranged;
    private boolean balcony;
    @ManyToOne
    private Lessor lessor;
}
