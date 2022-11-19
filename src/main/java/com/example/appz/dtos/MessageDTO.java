package com.example.appz.dtos;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
public class MessageDTO {
    @Min(0)
    private long id;
    private int senderId;
    private int receiverId;
    @NotBlank
    private String text;
}
