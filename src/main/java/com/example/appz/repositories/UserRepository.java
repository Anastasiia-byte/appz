package com.example.appz.repositories;

import com.example.appz.dtos.UserFullNameDTO;
import com.example.appz.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    @Query(value = "select * from users u join user_roles r on u.id = r.user_id where r.roles = 1", nativeQuery = true)
    List<User> getAllConsultants();

    @Query(value = "select u.id from users u join user_roles r on u.id = r.user_id where r.roles = 1", nativeQuery = true)
    List<Long> getAllConsultantIds();

    @Query(value = "select u.location from users u where u.email = :email", nativeQuery = true)
    String getUserLocationByEmail(String email);

    @Modifying
    @Query(value = "update users u set name = :name, surname = :surname, birth_date = :birthDate, location = :location where u.id = :id", nativeQuery = true)
    void update(String name, String surname, Date birthDate, String location, long id);

    @Modifying
    @Query(value = "update users u set number_of_rooms = :numberOfRooms, balcony = :balcony, arranged = :arranged where u.email = :email", nativeQuery = true)
    void fillUserInfo(Integer numberOfRooms, boolean balcony, boolean arranged, String email);

    @Query(value = "select u.name, u.surname from users u where u.id = :id", nativeQuery = true)
    UserFullNameDTO getUserFullNameById(long id);
}
