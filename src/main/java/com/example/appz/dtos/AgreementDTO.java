package com.example.appz.dtos;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AgreementDTO {
    private long id;
    private LocalDateTime date;
    private DwellingDTO dwelling;
    private String userSignature;
    private String lessorSignature;
    private boolean valid;
}
