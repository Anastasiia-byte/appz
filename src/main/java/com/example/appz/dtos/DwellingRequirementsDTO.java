package com.example.appz.dtos;

import lombok.Data;

import java.util.List;

@Data
public class DwellingRequirementsDTO {
    private List<DwellingDTO> dwellings;
    private boolean match;
}
