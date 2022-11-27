package com.example.appz.dtos;

import lombok.Data;

@Data
public class ChatUserDTO {
    private String receiverName;
    private String lastMessageSenderName;
    private ChatDTO chat;
}
