package com.example.appz.dtos.mappers;

import com.example.appz.dtos.LessorDTO;
import com.example.appz.entities.Lessor;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface LessorMapper {
    LessorMapper INSTANCE = Mappers.getMapper(LessorMapper.class);

    Lessor mapLessorDTO(LessorDTO lessorDTO);

    LessorDTO map(Lessor lessor);

    List<Lessor> mapLessorDTOList(List<LessorDTO> lessorDTOS);

    List<LessorDTO> map(List<Lessor> lessors);
}
