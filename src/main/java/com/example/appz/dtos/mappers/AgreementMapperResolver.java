package com.example.appz.dtos.mappers;

import com.example.appz.dtos.AgreementDTO;
import com.example.appz.entities.Agreement;
import org.mapstruct.ObjectFactory;
import org.springframework.stereotype.Component;

@Component
public class AgreementMapperResolver {

    @ObjectFactory
    public AgreementDTO resolve(Agreement agreement) {
        AgreementDTO agreementDTO = new AgreementDTO();

        agreementDTO.setDwelling(DwellingMapper.INSTANCE.map(agreement.getDwelling()));
        return agreementDTO;
    }
}
