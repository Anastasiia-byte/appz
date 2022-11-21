package com.example.appz.services;

import com.example.appz.dtos.MessageDTO;
import com.example.appz.dtos.mappers.MessageMapper;
import com.example.appz.entities.Message;
import com.example.appz.exceptions.EntityNotFoundException;
import com.example.appz.repositories.MessageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private NotificationService notificationService;

    public MessageDTO create(MessageDTO messageDTO) {
        log.info("Creating new message");
        Message message = MessageMapper.INSTANCE.mapMessageDto(messageDTO);
        Message savedMessage = messageRepository.save(message);

        MessageDTO createdMessage = MessageMapper.INSTANCE.map(savedMessage);
        notificationService.send(createdMessage);
        return createdMessage;
    }
}
