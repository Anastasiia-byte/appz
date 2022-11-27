package com.example.appz.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.Min;
import java.time.LocalDateTime;

@Data
public class UserAgreementDTO {
    @Min(0)
    private long id;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDateTime date;
    private DwellingDTO dwelling;
    private UserDTO user;
    private boolean complete;
}
