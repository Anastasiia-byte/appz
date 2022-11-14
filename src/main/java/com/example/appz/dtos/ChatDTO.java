package com.example.appz.dtos;

import com.example.appz.entities.Message;

import javax.persistence.OneToMany;
import java.util.List;

public class ChatDTO {
    private long id;
    private List<MessageDTO> messages;
}
