package com.example.appz.services;

import com.example.appz.dtos.AgreementDTO;
import com.example.appz.dtos.mappers.AgreementMapper;
import com.example.appz.entities.Agreement;
import com.example.appz.exceptions.EntityNotFoundException;
import com.example.appz.repositories.AgreementRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class AgreementService {
    @Autowired
    private AgreementRepository agreementRepository;

    public AgreementDTO getById(long id) {
        log.info("Retrieving an agreement with id " + id);
        Agreement agreement = agreementRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Agreement with id " + id + " was not found"));

        return AgreementMapper.INSTANCE.map(agreement);
    }

    public AgreementDTO create(AgreementDTO agreementDTO) {
        log.info("Creating an new agreement");
        Agreement agreement = AgreementMapper.INSTANCE.mapAgreementDto(agreementDTO);

        Agreement savedAgreement = agreementRepository.save(agreement);

        return AgreementMapper.INSTANCE.map(savedAgreement);
    }

    public void delete(long id) {
        log.info("Deleting an agreement with id " + id);
        agreementRepository.deleteById(id);
    }

    public List<AgreementDTO> getAll() {
        log.info("Retrieving all agreements");
        return AgreementMapper.INSTANCE.map(agreementRepository.findAll());
    }

    public AgreementDTO update(AgreementDTO agreementDTO) {
        log.info("Updating agreement with id " + agreementDTO.getId());
        Agreement agreement = AgreementMapper.INSTANCE.mapAgreementDto(agreementDTO);

        Agreement updatedAgreement = agreementRepository.save(agreement);

        return AgreementMapper.INSTANCE.map(updatedAgreement);
    }
}
