package com.example.appz.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "notifications")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    private User user;
    @OneToOne
    private Message message;
    private boolean read;
}
