package com.example.appz.services;

import com.example.appz.dtos.ChatDTO;
import com.example.appz.dtos.mappers.ChatMapper;
import com.example.appz.entities.Chat;
import com.example.appz.exceptions.EntityNotFoundException;
import com.example.appz.repositories.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatService {
    @Autowired
    private ChatRepository chatRepository;

    public ChatDTO getById(long id) {
        Chat chat = chatRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Chat with id " + id + " was not found"));

        return ChatMapper.INSTANCE.mapChat(chat);
    }
}
