package com.example.appz.entities;

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
    private long userId;
    private byte[] publicKey;
    private byte[] userSignature;
    private byte[] lessorSignature;
    private boolean complete;
}
