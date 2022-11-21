package com.example.appz.controllers;

import com.example.appz.dtos.DwellingDTO;
import com.example.appz.services.DwellingService;
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
@RequestMapping("/api/dwelling")
@Validated
public class DwellingController {
    @Autowired
    private DwellingService dwellingService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public DwellingDTO getById(@PathVariable @Min(0) long id) {
        log.info("Received request to get dwelling by id " + id);
        return dwellingService.getById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<DwellingDTO> getAll() {
        log.info("Received request to ger all dwellings");
        return dwellingService.getAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public DwellingDTO create(@Valid @RequestBody DwellingDTO dwellingDTO) {
        log.info("Received request to create new dwelling");
        return dwellingService.create(dwellingDTO);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping
    public DwellingDTO update(@Valid @RequestBody DwellingDTO dwellingDTO) {
        log.info("Received request to update dwelling with id " + dwellingDTO.getId());
        return dwellingService.update(dwellingDTO);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable @Min(0) long id) {
        log.info("Received request to delete dwelling with id " + id);
        dwellingService.delete(id);
    }
}
