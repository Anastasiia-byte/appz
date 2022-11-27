package com.example.appz.services;

import com.example.appz.dtos.*;
import com.example.appz.dtos.mappers.AgreementMapper;
import com.example.appz.dtos.mappers.LessorMapper;
import com.example.appz.dtos.mappers.UserAgreementMapper;
import com.example.appz.entities.Agreement;
import com.example.appz.entities.Lessor;
import com.example.appz.exceptions.AgreementCreationException;
import com.example.appz.exceptions.EntityNotFoundException;
import com.example.appz.repositories.AgreementRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class AgreementService {

    @Value("${spring.mail.username}")
    private String emailFrom;

    @Value("${spring.mail.dwelling.subject}")
    private String subject;

    @Autowired
    private AgreementRepository agreementRepository;

    @Autowired
    private DwellingService dwellingService;

    @Autowired
    private DigitalSignatureService digitalSignatureService;

    @Autowired
    private UserService userService;

    @Autowired
    private LessorService lessorService;
    
    @Autowired
    private AgreementMapper agreementMapper;

    @Autowired
    private UserAgreementMapper userAgreementMapper;

    @Autowired
    private EmailService emailService;

    public AgreementDTO getById(long id) {
        log.info("Retrieving an agreement with id " + id);
        Agreement agreement = agreementRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Agreement with id " + id + " was not found"));

        return agreementMapper.map(agreement);
    }

    @Transactional
    public AgreementDTO create(CreateAgreementDTO createAgreementDTO){
        log.info("Creating an new agreement");
        AgreementDTO agreementDTO = new AgreementDTO();
        agreementDTO.setDwelling(dwellingService.getById(createAgreementDTO.getDwellingId()));
        agreementDTO.setDate(LocalDateTime.now());
        UserDTO user = userService.getByEmail(createAgreementDTO.getUserEmail());
        agreementDTO.setUserId(user.getId());
        try {
            agreementDTO.setUserSignature(digitalSignatureService.createDigitalSignature(user));
        } catch (Exception e) {
            throw new AgreementCreationException("User digital signature was not verified");
        }
        agreementDTO.setPublicKey(digitalSignatureService.getPublicKey().getEncoded());
        agreementDTO.setComplete(false);

        return agreementMapper.map(agreementRepository.save(agreementMapper.mapAgreementDto(agreementDTO)));
    }

    public void delete(long id) {
        log.info("Deleting an agreement with id " + id);

        UserAgreementDTO agreement = userAgreementMapper.map(agreementRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Agreement with id " + id + " was not found")));
        agreementRepository.deleteById(id);

        emailService.send(agreement.getUser().getEmail(), subject, "Your dwelling agreement was declined. " +
                "Contact your consultant to receive more details.", emailFrom);
    }

    public List<AgreementDTO> getAll() {
        log.info("Retrieving all agreements");
        return agreementMapper.map(agreementRepository.findAll());
    }

    public List<UserAgreementDTO> getAllByComplete(boolean complete) {
        log.info("Retrieving all agreements where complete is " + complete);
        return userAgreementMapper.map(agreementRepository.findAllByCompleteIs(complete));
    }

    @Transactional
    public UserAgreementDTO update(long id) {
        AgreementDTO agreementDTO = getById(id);

        log.info("Updating agreement with id " + agreementDTO.getId());

        PublicKey publicKey;
        try {
           publicKey = digitalSignatureService.setPublicKey(agreementDTO.getPublicKey());
        } catch (NoSuchAlgorithmException | InvalidKeySpecException exception) {
            throw new AgreementCreationException("Error when creating agreement");
        }

        UserDTO userDTO = userService.getById(agreementDTO.getUserId());

        try {
            digitalSignatureService.verifySignature(agreementDTO.getUserSignature(), userDTO, publicKey);
        }
        catch (Exception exception) {
            throw new AgreementCreationException("User digital signature was not verified");
        }

        try {
            agreementDTO.setLessorSignature(digitalSignatureService.createDigitalSignature(lessorService.getByDwellingId(agreementDTO.getDwelling().getId())));
        } catch (Exception exception) {
            throw new AgreementCreationException("Error when creating agreement");
        }

        agreementDTO.setComplete(true);
        UserAgreementDTO agreement = userAgreementMapper.map(agreementRepository.save(agreementMapper.mapAgreementDto(agreementDTO)));

        emailService.send(agreement.getUser().getEmail(), subject, "Your dwelling agreement was accepted! " +
                "Contact your consultant to receive more details!", emailFrom);

        return agreement;
    }
}
