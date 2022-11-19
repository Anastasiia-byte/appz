package com.example.appz.dtos.mappers;

import com.example.appz.dtos.MessageDTO;
import com.example.appz.entities.Message;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface MessageMapper {
    MessageMapper INSTANCE = Mappers.getMapper(MessageMapper.class);

    Message mapMessageDto(MessageDTO messageDTO);

    MessageDTO map(Message message);

    List<Message> mapMessageDtoList(List<MessageDTO> messageDtoList);

    List<MessageDTO> map(List<Message> messageList);
}
