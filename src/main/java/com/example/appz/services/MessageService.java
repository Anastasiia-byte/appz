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

    public MessageDTO getById(long id) {
        log.info("Retrieving message with id " + id);
        Message message = messageRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Message with id " + id + " was not found"));

        return MessageMapper.INSTANCE.mapMessage(message);
    }

    public List<MessageDTO> getAll() {
        log.info("Retrieving all messages");
        List<Message> allMessages = messageRepository.findAll();

        return MessageMapper.INSTANCE.mapMessageList(allMessages);
    }

    public MessageDTO create(MessageDTO messageDTO) {
        log.info("Creating new message");
        Message message = MessageMapper.INSTANCE.mapMessageDto(messageDTO);

        Message savedMessage = messageRepository.save(message);

        return MessageMapper.INSTANCE.mapMessage(savedMessage);
    }
}
