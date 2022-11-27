package com.example.appz.dtos.mappers;

import com.example.appz.dtos.UserAgreementDTO;
import com.example.appz.entities.Agreement;
import com.example.appz.services.UserService;
import org.mapstruct.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserAgreementResolver {
    @Autowired
    private DwellingMapper dwellingMapper;

    @Autowired
    private UserService userService;

    @ObjectFactory
    public UserAgreementDTO resolve(Agreement agreement) {
        UserAgreementDTO userAgreementDTO = new UserAgreementDTO();

        userAgreementDTO.setDwelling(dwellingMapper.map(agreement.getDwelling()));
        userAgreementDTO.setUser(userService.getById(agreement.getUserId()));
        return userAgreementDTO;
    }
}
