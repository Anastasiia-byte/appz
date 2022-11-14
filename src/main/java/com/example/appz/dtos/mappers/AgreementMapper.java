package com.example.appz.dtos.mappers;

import com.example.appz.dtos.AgreementDTO;
import com.example.appz.entities.Agreement;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface AgreementMapper {
    AgreementMapper INSTANCE = Mappers.getMapper(AgreementMapper.class);

    Agreement mapAgreementDto(AgreementDTO agreementDTO);

    AgreementDTO mapAgreement(Agreement agreement);

    List<Agreement> mapAgreementDtoList(List<AgreementDTO> agreementDtoList);

    List<AgreementDTO> mapAgreementList(List<Agreement> agreementList);
}
