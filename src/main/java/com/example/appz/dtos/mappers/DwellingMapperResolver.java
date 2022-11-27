package com.example.appz.dtos.mappers;

import com.example.appz.dtos.DwellingDTO;
import com.example.appz.entities.Dwelling;
import org.mapstruct.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DwellingMapperResolver {
    @Autowired
    private LessorMapper lessorMapper;

    @ObjectFactory
    public DwellingDTO resolve(Dwelling dwelling) {
        DwellingDTO dwellingDTO = new DwellingDTO();

        dwellingDTO.setLessor(lessorMapper.map(dwelling.getLessor()));

        return dwellingDTO;
    }
}
