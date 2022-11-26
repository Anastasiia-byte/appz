package com.example.appz.services;

import com.example.appz.dtos.MessageDTO;
import com.example.appz.dtos.mappers.MessageMapper;
import com.example.appz.entities.Message;
import com.example.appz.exceptions.EntityNotFoundException;
import com.example.appz.repositories.MessageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private ChatService chatService;

    @Autowired
    private MessageMapper messageMapper;

    @Transactional
    public MessageDTO create(MessageDTO messageDTO, long chatId) {
        log.info("Creating new message");
        Message message = messageMapper.mapMessageDto(messageDTO);
        Message savedMessage = messageRepository.save(message);

        MessageDTO createdMessage = messageMapper.map(savedMessage);
        notificationService.send(createdMessage, chatId);
        chatService.addMessage(createdMessage, chatId);
        return createdMessage;
    }
}
