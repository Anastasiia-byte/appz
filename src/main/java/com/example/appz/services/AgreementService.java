package com.example.appz.services;

import com.example.appz.dtos.AgreementDTO;
import com.example.appz.dtos.CreateAgreementDTO;
import com.example.appz.dtos.mappers.AgreementMapper;
import com.example.appz.entities.Agreement;
import com.example.appz.exceptions.EntityNotFoundException;
import com.example.appz.repositories.AgreementRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class AgreementService {
    @Autowired
    private AgreementRepository agreementRepository;

    @Autowired
    private DwellingService dwellingService;

    @Autowired
    private DigitalSignatureService digitalSignatureService;

    @Autowired
    private UserService userService;

    public AgreementDTO getById(long id) {
        log.info("Retrieving an agreement with id " + id);
        Agreement agreement = agreementRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Agreement with id " + id + " was not found"));

        return AgreementMapper.INSTANCE.map(agreement);
    }

    @Transactional
    public AgreementDTO create(CreateAgreementDTO createAgreementDTO) {
        log.info("Creating an new agreement");
        AgreementDTO agreementDTO = new AgreementDTO();
        agreementDTO.setDwelling(dwellingService.getById(createAgreementDTO.getDwellingId()));
        agreementDTO.setDate(LocalDateTime.now());
//        AgreementDTO.builder()
//                .;
//
//
//
//        Agreement agreement = AgreementMapper.INSTANCE.mapAgreementDto(agreementDTO);
//
//        Agreement savedAgreement = agreementRepository.save(agreement);
//
//        return AgreementMapper.INSTANCE.map(savedAgreement);
        return null;
    }

    public void delete(long id) {
        log.info("Deleting an agreement with id " + id);
        agreementRepository.deleteById(id);
    }

    public List<AgreementDTO> getAll() {
        log.info("Retrieving all agreements");
        return AgreementMapper.INSTANCE.map(agreementRepository.findAll());
    }

    @Transactional
    public AgreementDTO update(AgreementDTO agreementDTO) {
        log.info("Updating agreement with id " + agreementDTO.getId());
        Agreement agreement = AgreementMapper.INSTANCE.mapAgreementDto(agreementDTO);

        Agreement updatedAgreement = agreementRepository.save(agreement);

        return AgreementMapper.INSTANCE.map(updatedAgreement);
    }
}
