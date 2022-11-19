package com.example.appz.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
public class AgreementDTO {
    @Min(0)
    private long id;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDateTime date;
    @NotBlank
    private String userSignature;
    @NotBlank
    private String lessorSignature;
    private boolean valid;
}
