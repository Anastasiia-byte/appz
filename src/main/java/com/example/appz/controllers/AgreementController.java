package com.example.appz.controllers;

import com.example.appz.dtos.AgreementDTO;
import com.example.appz.dtos.CreateAgreementDTO;
import com.example.appz.dtos.UserAgreementDTO;
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
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/agreement")
@Validated
public class AgreementController {
    @Autowired
    private AgreementService agreementService;

    @CrossOrigin(origins = "*")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("{id}")
    public AgreementDTO getById(@PathVariable @Min(0) long id) {
        log.info("Received request to get agreement with id " + id);
        return agreementService.getById(id);
    }

    @CrossOrigin(origins = "*")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<AgreementDTO> getAll() {
        log.info("Received request to get all agreements");
        return agreementService.getAll();
    }

    @CrossOrigin(origins = "*")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/complete")
    public List<UserAgreementDTO> getAllByComplete(@RequestParam("complete") boolean complete) {
        log.info("Received request to get all agreements");
        return agreementService.getAllByComplete(complete);
    }

    @CrossOrigin(origins = "*")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public AgreementDTO create(@Valid @RequestBody CreateAgreementDTO createAgreementDTO) {
        log.info("Received request to create new agreement for user " + createAgreementDTO.getUserEmail());
        return agreementService.create(createAgreementDTO);
    }

    @CrossOrigin(origins = "*")
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/update/{id}")
    public UserAgreementDTO update(@PathVariable long id) {
        log.info("Received request to update agreement with id " + id);
        return agreementService.update(id);
    }

    @CrossOrigin(origins = "*")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable @Min(0) long id) {
        log.info("Received request to delete agreement with id " + id);
        agreementService.delete(id);
    }
}
