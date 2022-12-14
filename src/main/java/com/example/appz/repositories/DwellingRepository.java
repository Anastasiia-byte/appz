package com.example.appz.repositories;

import com.example.appz.entities.Dwelling;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Repository
public interface DwellingRepository extends JpaRepository<Dwelling, Long> {
    List<Dwelling> findAllByLocation(String location);

    List<Dwelling> findAllByLocationAndNumberOfRooms(String location, Integer numberOfRooms);
    List<Dwelling> findDwellingByLocationAndNumberOfRoomsAndBalconyIs(String location, Integer numberOfRooms, boolean balcony);
    List<Dwelling> findDwellingByLocationAndNumberOfRoomsAndArrangedIs(String location, Integer numberOfRooms, boolean arranged);
    List<Dwelling> findDwellingByLocationAndNumberOfRoomsAndBalconyIsAndArrangedIs(String location, Integer numberOfRooms, boolean balcony, boolean arranged);
}
