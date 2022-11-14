package com.example.appz.dtos;

import com.example.appz.entities.Role;
import lombok.Data;

import java.util.Date;
import java.util.Set;

@Data
public class UserDTO {
    private long id;
    private String name;
    private String surname;
    private String email;
    private Date birthDate;

    private Set<Role> roles;
}
