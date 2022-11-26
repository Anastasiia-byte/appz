package com.example.appz.controllers;

import com.example.appz.dtos.MessageDTO;
import com.example.appz.services.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@CrossOrigin(origins = "*")
@RestController
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/chat/{id}")
    @SendTo("/queue/{id}")
    public void send(@Valid MessageDTO messageDTO, @DestinationVariable("id") long chatId) {
        log.info("Received a message. Chat id - " + chatId);
        MessageDTO lol = messageService.create(messageDTO, chatId);
        simpMessagingTemplate.convertAndSend("/queue/" + chatId, lol);
    }
}
