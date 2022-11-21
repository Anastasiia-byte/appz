package com.example.appz.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "messages")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    private Chat chat;
    @ManyToOne
    private User sender;
    @ManyToOne
    private User receiver;
    private String text;
}
