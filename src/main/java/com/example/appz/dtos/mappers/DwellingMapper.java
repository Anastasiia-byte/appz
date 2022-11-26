package com.example.appz.dtos.mappers;

import com.example.appz.dtos.DwellingDTO;
import com.example.appz.entities.Dwelling;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DwellingMapper {
    Dwelling mapDwellingDto(DwellingDTO agreementDTO);

    DwellingDTO map(Dwelling agreement);

    List<Dwelling> mapDwellingDtoList(List<DwellingDTO> dwellingDtoList);

    List<DwellingDTO> map(List<Dwelling> dwellingList);
}
