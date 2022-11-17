package com.example.appz.dtos;

import lombok.Data;

import java.util.List;

@Data
public class ChatDTO {
    private long id;
    private List<MessageDTO> messages;
}
