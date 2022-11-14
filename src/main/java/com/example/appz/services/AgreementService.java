package com.example.appz.services;

import com.example.appz.dtos.AgreementDTO;
import com.example.appz.dtos.mappers.AgreementMapper;
import com.example.appz.entities.Agreement;
import com.example.appz.exceptions.EntityNotFoundException;
import com.example.appz.repositories.AgreementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgreementService {
    @Autowired
    private AgreementRepository agreementRepository;

    public AgreementDTO getById(long id) {
        Agreement agreement = agreementRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Agreement with id " + id + " was not found"));

        return AgreementMapper.INSTANCE.mapAgreement(agreement);
    }

    public AgreementDTO save(AgreementDTO agreementDTO) {
        Agreement agreement = AgreementMapper.INSTANCE.mapAgreementDto(agreementDTO);

        Agreement savedAgreement = agreementRepository.save(agreement);

        return AgreementMapper.INSTANCE.mapAgreement(savedAgreement);
    }

    public void delete(long id) {
        agreementRepository.deleteById(id);
    }

    public List<AgreementDTO> getAll() {
        return AgreementMapper.INSTANCE.mapAgreementList(agreementRepository.findAll());
    }

    public AgreementDTO update(AgreementDTO agreementDTO) {
        Agreement agreement = AgreementMapper.INSTANCE.mapAgreementDto(agreementDTO);

        Agreement updatedAgreement = agreementRepository.save(agreement);

        return AgreementMapper.INSTANCE.mapAgreement(updatedAgreement);
    }
}
