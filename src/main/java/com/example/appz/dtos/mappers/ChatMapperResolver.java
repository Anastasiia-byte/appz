package com.example.appz.dtos.mappers;

import com.example.appz.dtos.ChatDTO;
import com.example.appz.entities.Chat;
import org.mapstruct.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ChatMapperResolver {
    @Autowired
    private MessageMapper messageMapper;

    @ObjectFactory
    public ChatDTO resolve(Chat chat) {
        ChatDTO chatDTO = new ChatDTO();

        if (chat.getMessages() != null) {
            chatDTO.setMessages(messageMapper.map(chat.getMessages()));
        }

        return chatDTO;
    }
}
