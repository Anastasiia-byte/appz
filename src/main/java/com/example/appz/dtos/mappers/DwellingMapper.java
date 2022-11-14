package com.example.appz.dtos.mappers;

import com.example.appz.dtos.DwellingDTO;
import com.example.appz.entities.Dwelling;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface DwellingMapper {
    DwellingMapper INSTANCE = Mappers.getMapper(DwellingMapper.class);

    Dwelling mapDwellingDto(DwellingDTO agreementDTO);

    DwellingDTO mapDwelling(Dwelling agreement);

    List<Dwelling> mapDwellingDtoList(List<DwellingDTO> dwellingDtoList);

    List<DwellingDTO> mapDwellingList(List<Dwelling> dwellingList);
}
