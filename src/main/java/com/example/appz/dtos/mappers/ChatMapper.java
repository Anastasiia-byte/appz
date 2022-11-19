package com.example.appz.dtos.mappers;

import com.example.appz.dtos.ChatDTO;
import com.example.appz.entities.Chat;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = ChatMapperResolver.class)
public interface ChatMapper {
    ChatMapper INSTANCE = Mappers.getMapper(ChatMapper.class);

    Chat mapChatDto(ChatDTO chatDTO);

    ChatDTO map(Chat chat);

    List<Chat> mapChatDtoList(List<ChatDTO> chatDtoList);

    List<ChatDTO> map(List<Chat> chatList);
}
