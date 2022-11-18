package com.example.appz.dtos;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
public class ChatDTO {
    @Min(0)
    private long id;
    @NotEmpty
    private List<MessageDTO> messages;
}
