package com.example.appz.dtos.mappers;

import com.example.appz.dtos.UserAgreementDTO;
import com.example.appz.entities.Agreement;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {UserAgreementResolver.class})
public interface UserAgreementMapper {
    UserAgreementDTO map(Agreement agreement);

    List<UserAgreementDTO> map(List<Agreement> agreement);
}
