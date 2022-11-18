package com.example.appz.dtos;

import com.example.appz.entities.Role;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.*;
import java.util.Date;
import java.util.Set;

@Data
public class UserDTO {
    @Min(0)
    private long id;
    @NotBlank
    @Size(min = 2)
    private String name;
    @NotBlank
    @Size(min = 2)
    private String surname;
    @Email
    private String email;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date birthDate;
    @NotEmpty
    private Set<Role> roles;
}
