package com.example.appz.dtos.mappers;

import com.example.appz.dtos.UserDTO;
import com.example.appz.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User mapUserDTO(UserDTO userDTO);

    UserDTO mapUser(User user);

    List<User> mapUserDTOList(List<UserDTO> userDTOS);

    List<UserDTO> mapUserList(List<User> users);
}
