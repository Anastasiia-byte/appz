package com.example.appz.dtos.mappers;

import com.example.appz.dtos.MessageDTO;
import com.example.appz.entities.Message;
import com.example.appz.services.ChatService;
import com.example.appz.services.UserService;
import org.mapstruct.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageMapperResolver {
    @Autowired
    private ChatService chatService;

    @Autowired
    private UserService userService;

    @Autowired
    private ChatMapper chatMapper;

    @Autowired
    private UserMapper userMapper;

    @ObjectFactory
    public MessageDTO resolve(Message message) {
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setSenderId(message.getSender().getId());
        messageDTO.setReceiverId(message.getReceiver().getId());

        return messageDTO;
    }

    @ObjectFactory
    public Message resolve(MessageDTO messageDTO) {
        Message message = new Message();
        message.setSender(userMapper.mapUserDTO(userService.getById(messageDTO.getSenderId())));
        message.setReceiver(userMapper.mapUserDTO(userService.getById(messageDTO.getReceiverId())));

        return message;
    }
}
