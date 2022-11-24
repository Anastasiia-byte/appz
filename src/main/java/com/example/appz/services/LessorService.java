package com.example.appz.services;

import com.example.appz.dtos.LessorDTO;
import com.example.appz.dtos.mappers.LessorMapper;
import com.example.appz.entities.Lessor;
import com.example.appz.exceptions.EntityNotFoundException;
import com.example.appz.repositories.LessorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class LessorService {

    @Autowired
    private LessorRepository lessorRepository;

    public LessorDTO getById(long id) {
        log.info("Retrieving lessor with id " + id);
        return LessorMapper.INSTANCE.map(lessorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Lessor with id " + id + " was not found")));
    }

    public List<LessorDTO> getAll() {
        log.info("Retrieving all lessors");
        return LessorMapper.INSTANCE.map(lessorRepository.findAll());
    }

    public LessorDTO create(LessorDTO lessorDTO) {
        log.info("Creating new lessor with email " + lessorDTO.getEmail());
        Lessor lessor = lessorRepository.save(LessorMapper.INSTANCE.mapLessorDTO(lessorDTO));
        return LessorMapper.INSTANCE.map(lessor);
    }

    @Transactional
    public LessorDTO update(LessorDTO lessorDTO) {
        log.info("Updating lessor with id " + lessorDTO.getId());
        return LessorMapper.INSTANCE.map(lessorRepository.save(LessorMapper.INSTANCE.mapLessorDTO(lessorDTO)));
    }

    public void delete(long id) {
        log.info("Deleting lessor with id " + id);
        lessorRepository.deleteById(id);
    }

    public LessorDTO getByDwellingId(long dwellingId) {
        log.info("Getting lessor by dwelling id " + dwellingId);
        return LessorMapper.INSTANCE.map(lessorRepository.getByDwellingId(dwellingId));
    }
}
