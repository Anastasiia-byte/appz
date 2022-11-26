package com.example.appz.dtos.mappers;

import com.example.appz.dtos.MessageDTO;
import com.example.appz.entities.Message;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", uses = {MessageMapperResolver.class})
public interface MessageMapper {
    Message mapMessageDto(MessageDTO messageDTO);

    MessageDTO map(Message message);

    List<Message> mapMessageDtoList(List<MessageDTO> messageDtoList);

    List<MessageDTO> map(List<Message> messageList);
}
