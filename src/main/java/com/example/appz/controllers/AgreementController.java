package com.example.appz.controllers;

import com.example.appz.dtos.AgreementDTO;
import com.example.appz.dtos.CreateAgreementDTO;
import com.example.appz.services.AgreementService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/agreement")
@Validated
public class AgreementController {
    @Autowired
    private AgreementService agreementService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("{id}")
    public AgreementDTO getById(@PathVariable @Min(0) long id) {
        log.info("Received request to get agreement with id " + id);
        return agreementService.getById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<AgreementDTO> getAll() {
        log.info("Received request to get all agreements");
        return agreementService.getAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public AgreementDTO create(@Valid @RequestBody CreateAgreementDTO createAgreementDTO) {
        log.info("Received request to create new agreement for user " + createAgreementDTO.getUserId());
        return null;
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping
    public AgreementDTO update(@Valid @RequestBody AgreementDTO agreementDTO) {
        log.info("Received request to update agreement with id " + agreementDTO.getId());
        return agreementService.update(agreementDTO);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public void delete(@PathVariable @Min(0) long id) {
        log.info("Received request to delete agreement with id " + id);
        agreementService.delete(id);
    }
}
