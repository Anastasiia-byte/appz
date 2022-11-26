package com.example.appz.services;

import com.example.appz.dtos.ChatDTO;
import com.example.appz.dtos.MessageDTO;
import com.example.appz.dtos.mappers.ChatMapper;
import com.example.appz.dtos.mappers.MessageMapper;
import com.example.appz.entities.Chat;
import com.example.appz.exceptions.EntityNotFoundException;
import com.example.appz.repositories.ChatRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class ChatService {
    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private MessageMapper messageMapper;
    
    @Autowired
    private ChatMapper chatMapper;

    public ChatDTO getById(long id) {
        log.info("Retrieving chat with id " + id);
        Chat chat = chatRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Chat with id " + id + " was not found"));

        return chatMapper.map(chat);
    }

    public List<ChatDTO> getAllForUser(long userId) {
        log.info("Retrieving al chats");
        List<Chat> allChats = chatRepository.getAllByUser(userId);

        return chatMapper.map(allChats);
    }

    public ChatDTO create() {
        log.info("Creating new chat");
//        Chat chat = chatMapper.mapChatDto(chatDTO);

        Chat chat = new Chat();

        Chat savedChat = chatRepository.save(chat);

        return chatMapper.map(savedChat);
    }

    @Transactional
    public ChatDTO update(ChatDTO chatDTO) {
        log.info("Updating chat with id " + chatDTO.getId());
        Chat chat = chatMapper.mapChatDto(chatDTO);

        Chat updatedChat = chatRepository.save(chat);

        return chatMapper.map(updatedChat);
    }

    public void delete(long id) {
        log.info("Deleting chat with id " + id);
        chatRepository.deleteById(id);
    }

    public void deleteByIds(List<Long> ids) {
        log.info("Deleting chats by ids");
        chatRepository.deleteAllById(ids);
    }

    @Transactional
    public void addMessage(MessageDTO messageDTO, long chatId) {
        log.info("Adding new message to the chat " + chatId);
        Chat chat = chatRepository.findById(chatId)
                .orElseThrow(() -> new EntityNotFoundException("No chat with id " + chatId + " was found"));
        chat.getMessages().add(messageMapper.mapMessageDto(messageDTO));
        chatRepository.save(chat);
    }
}
