package com.example.appz.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "lessors")
public class Lessor {
    @Id
    private long id;
    private String name;
    private String surname;
    private String email;
}
