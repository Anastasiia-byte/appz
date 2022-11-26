package com.example.appz.dtos.mappers;

import com.example.appz.dtos.AgreementDTO;
import com.example.appz.entities.Agreement;
import org.mapstruct.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AgreementMapperResolver {
    @Autowired
    private DwellingMapper dwellingMapper;

    @ObjectFactory
    public AgreementDTO resolve(Agreement agreement) {
        AgreementDTO agreementDTO = new AgreementDTO();

        agreementDTO.setDwelling(dwellingMapper.map(agreement.getDwelling()));
        return agreementDTO;
    }
}
