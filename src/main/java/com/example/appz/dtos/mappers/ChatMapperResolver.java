package com.example.appz.dtos.mappers;

import com.example.appz.dtos.ChatDTO;
import com.example.appz.entities.Chat;
import org.mapstruct.ObjectFactory;
import org.springframework.stereotype.Component;

@Component
public class ChatMapperResolver {

    @ObjectFactory
    public ChatDTO resolve(Chat chat) {
        ChatDTO chatDTO = new ChatDTO();

        chatDTO.setMessages(MessageMapper.INSTANCE.map(chat.getMessages()));
        return chatDTO;
    }
}
