package com.example.appz.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.validation.constraints.Min;
import java.time.LocalDateTime;

@Data
public class AgreementDTO {
    @Min(0)
    private long id;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDateTime date;
    private DwellingDTO dwelling;
    private long userId;
    private byte[] publicKey;
    private byte[] userSignature;
    private byte[] lessorSignature;
    private boolean complete;
}
