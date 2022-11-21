package com.example.appz.controllers;

import com.example.appz.dtos.LessorDTO;
import com.example.appz.services.LessorService;
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
@RequestMapping("/api/lessor")
@Validated
public class LessorController {

    @Autowired
    private LessorService lessorService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public LessorDTO getById(@PathVariable @Min(0) long id) {
        log.info("Received request to get lessor by id " + id);
        return lessorService.getById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<LessorDTO> getAll() {
        log.info("Received request to get all lessors");
        return lessorService.getAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public LessorDTO create(@RequestBody @Valid LessorDTO lessorDTO) {
        log.info("Received request to create a new lessor with email " + lessorDTO.getEmail());
        return lessorService.create(lessorDTO);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping
    public LessorDTO update(@RequestBody @Valid LessorDTO lessorDTO) {
        log.info("Received request to update lessor with id " + lessorDTO.getId());
        return lessorService.update(lessorDTO);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable @Min(0) long id) {
        log.info("Received request to delete lessor with id " + id);
        lessorService.delete(id);
    }
}
