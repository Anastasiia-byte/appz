package com.example.appz.services;

import com.example.appz.dtos.DwellingDTO;
import com.example.appz.dtos.mappers.DwellingMapper;
import com.example.appz.entities.Dwelling;
import com.example.appz.exceptions.EntityNotFoundException;
import com.example.appz.repositories.DwellingRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class DwellingService {
    @Autowired
    private DwellingRepository dwellingRepository;

    public DwellingDTO getById(long id) {
        log.info("Retrieving dwelling with id " + id);
        Dwelling dwelling = dwellingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Dwelling with id " + id + " was not found"));

        return DwellingMapper.INSTANCE.mapDwelling(dwelling);
    }

    public List<DwellingDTO> getAll() {
        log.info("Retrieving all dwellings");
        List<Dwelling> allDwellings = dwellingRepository.findAll();

        return DwellingMapper.INSTANCE.mapDwellingList(allDwellings);
    }

    public DwellingDTO create(DwellingDTO dwellingDTO) {
        log.info("Creating new dwelling");
        Dwelling dwelling = DwellingMapper.INSTANCE.mapDwellingDto(dwellingDTO);

        Dwelling savedDwelling = dwellingRepository.save(dwelling);

        return DwellingMapper.INSTANCE.mapDwelling(savedDwelling);
    }

    public DwellingDTO update(DwellingDTO dwellingDTO) {
        log.info("Updating dwelling with id " + dwellingDTO.getId());
        Dwelling dwelling = DwellingMapper.INSTANCE.mapDwellingDto(dwellingDTO);

        Dwelling updatedDwelling = dwellingRepository.save(dwelling);

        return DwellingMapper.INSTANCE.mapDwelling(updatedDwelling);
    }

    public void delete(long id) {
        log.info("Deleting dwelling with id " + id);
        dwellingRepository.deleteById(id);
    }
}
