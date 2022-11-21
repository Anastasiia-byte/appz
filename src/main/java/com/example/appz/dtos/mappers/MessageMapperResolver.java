package com.example.appz.dtos.mappers;

import com.example.appz.dtos.MessageDTO;
import com.example.appz.entities.Message;
import org.mapstruct.ObjectFactory;
import org.springframework.stereotype.Component;

@Component
public class MessageMapperResolver {

    @ObjectFactory
    public MessageDTO resolve(Message message) {
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setChatId(message.getChat().getId());
        messageDTO.setSenderId(message.getSender().getId());
        messageDTO.setReceiverId(message.getReceiver().getId());

        return messageDTO;
    }
}
