package com.example.appz.controllers;

import com.example.appz.dtos.MessageDTO;
import com.example.appz.services.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
public class MessageController {

    @Autowired
    private MessageService messageService;

    @MessageMapping("/chat/{id}")
    @SendTo("/queue/{id}")
    public MessageDTO send(@Valid MessageDTO messageDTO) {
        log.info("Received a message. Chat id - " + messageDTO.getId());
        return messageService.create(messageDTO);
    }
}
