package com.example.appz.controllers;

import com.example.appz.dtos.ChatDTO;
import com.example.appz.services.ChatService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Slf4j
@CrossOrigin(origins = "http://localhost:4200")
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

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/all/{userId}")
    public List<ChatDTO> getAllForUser(@PathVariable @Min(0) long userId) {
        log.info("Received request to get all chats for user " + userId);
        return chatService.getAllForUser(userId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ChatDTO create() {
        log.info("Received request to create new chat");
        return chatService.create();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable @Min(0) long id) {
        log.info("Received request to delete chat " + id);
        chatService.delete(id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping
    public void deleteByIds(@RequestBody @NotEmpty List<Long> ids) {
        log.info("Received request to delete chats by ids");
        chatService.deleteByIds(ids);
    }
}
