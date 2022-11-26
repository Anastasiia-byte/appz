package com.example.appz.dtos.mappers;

import com.example.appz.dtos.AgreementDTO;
import com.example.appz.entities.Agreement;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", uses = {AgreementMapperResolver.class})
public interface AgreementMapper {
    Agreement mapAgreementDto(AgreementDTO agreementDTO);

    AgreementDTO map(Agreement agreement);

    List<Agreement> mapAgreementDtoList(List<AgreementDTO> agreementDtoList);

    List<AgreementDTO> map(List<Agreement> agreementList);
}
