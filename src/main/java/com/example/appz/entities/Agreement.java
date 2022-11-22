package com.example.appz.entities;

import com.example.appz.dtos.DwellingDTO;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "agreements")
public class Agreement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private LocalDateTime date;
    @OneToOne
    private Dwelling dwelling;
    private String userSignature;
    private String lessorSignature;
    private boolean complete;
}
