package com.example.appz.dtos.mappers;

import com.example.appz.dtos.UserDTO;
import com.example.appz.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User mapUserDTO(UserDTO userDTO);

    UserDTO map(User user);

    List<User> mapUserDTOList(List<UserDTO> userDTOS);

    List<UserDTO> map(List<User> users);
}
