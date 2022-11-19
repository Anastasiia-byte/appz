package com.example.appz.dtos;

import lombok.Data;

import javax.validation.constraints.Min;

@Data
public class ChatDTO {
    @Min(0)
    private long id;
}
