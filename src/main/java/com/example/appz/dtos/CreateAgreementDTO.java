package com.example.appz.dtos;

import lombok.Data;

import javax.validation.constraints.Min;


@Data
public class CreateAgreementDTO {
    @Min(0)
    private long userId;
    @Min(0)
    private long dwellingId;
}
