package com.example.appz.dtos;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;


@Data
public class CreateAgreementDTO {
    @NotBlank
    private String userEmail;
    @Min(0)
    private long dwellingId;
}
