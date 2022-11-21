package com.example.appz.controllers;

import com.example.appz.dtos.ChatDTO;
import com.example.appz.services.ChatService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/chat")
@Validated
public class ChatController {

    @Autowired
    private ChatService chatService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public ChatDTO getById(@PathVariable @Min(0) long id) {
        log.info("Received request to get chat by id " + id);
        return chatService.getById(id);
    }

    public List<ChatDTO> getAllForUser(@PathVariable @Min(0) long userId) {
        log.info("");
    }

}
