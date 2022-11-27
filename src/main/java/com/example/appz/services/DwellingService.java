package com.example.appz.services;

import com.example.appz.dtos.DwellingDTO;
import com.example.appz.dtos.DwellingRequirementsDTO;
import com.example.appz.dtos.UserDTO;
import com.example.appz.dtos.mappers.DwellingMapper;
import com.example.appz.entities.Dwelling;
import com.example.appz.exceptions.EntityNotFoundException;
import com.example.appz.repositories.DwellingRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class DwellingService {
    @Autowired
    private DwellingRepository dwellingRepository;

    @Autowired
    private UserService userService;
    
    @Autowired
    private DwellingMapper dwellingMapper;

    @Autowired
    private EmailService emailService;

    public DwellingDTO getById(long id) {
        log.info("Retrieving dwelling with id " + id);
        Dwelling dwelling = dwellingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Dwelling with id " + id + " was not found"));

        return dwellingMapper.map(dwelling);
    }

    public DwellingRequirementsDTO getAll(boolean filter) {
        log.info("Retrieving all dwellings");

        DwellingRequirementsDTO dwellingRequirementsDTO = new DwellingRequirementsDTO();

        List<Dwelling> dwellings = new ArrayList<>();

        if (filter) {
            String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            UserDTO user = userService.getByEmail(email);

            dwellingRequirementsDTO.setMatch(true);

            if (user.isBalcony() && user.isArranged()) {
                dwellings = dwellingRepository.findDwellingByLocationAndNumberOfRoomsAndBalconyIsAndArrangedIs(user.getLocation(), user.getNumberOfRooms(), user.isBalcony(), user.isArranged());
            } else if (user.isArranged()) {
                dwellings = dwellingRepository.findDwellingByLocationAndNumberOfRoomsAndArrangedIs(user.getLocation(), user.getNumberOfRooms(), user.isArranged());
            } else if (user.isBalcony()) {
                dwellings = dwellingRepository.findDwellingByLocationAndNumberOfRoomsAndBalconyIs(user.getLocation(), user.getNumberOfRooms(), user.isBalcony());
            } else {
                dwellings = dwellingRepository.findAllByLocationAndNumberOfRooms(user.getLocation(), user.getNumberOfRooms());
            }

            if (dwellings.isEmpty()) {
                dwellings = dwellingRepository.findAllByLocation(user.getLocation());
            }
        }

        if (dwellings.isEmpty()) {
            dwellings = dwellingRepository.findAll();
            dwellingRequirementsDTO.setMatch(!filter);
        }

        List<DwellingDTO> dwellingDTOS = dwellingMapper.map(dwellings);

        dwellingRequirementsDTO.setDwellings(dwellingDTOS);

        return dwellingRequirementsDTO;
    }

    public DwellingDTO create(DwellingDTO dwellingDTO) {
        log.info("Creating new dwelling");
        Dwelling dwelling = dwellingMapper.mapDwellingDto(dwellingDTO);

        Dwelling savedDwelling = dwellingRepository.save(dwelling);

        return dwellingMapper.map(savedDwelling);
    }

    @Transactional
    public DwellingDTO update(DwellingDTO dwellingDTO) {
        log.info("Updating dwelling with id " + dwellingDTO.getId());
        Dwelling dwelling = dwellingMapper.mapDwellingDto(dwellingDTO);

        Dwelling updatedDwelling = dwellingRepository.save(dwelling);

        return dwellingMapper.map(updatedDwelling);
    }

    public void delete(long id) {
        log.info("Deleting dwelling with id " + id);
        dwellingRepository.deleteById(id);
    }
}
