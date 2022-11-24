package com.example.appz.repositories;

import com.example.appz.entities.Lessor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LessorRepository extends JpaRepository<Lessor, Long> {
    @Query(value = "select * from lessors l join dwellings d on l.id = d.lessor_id where d.id = :dwellingId", nativeQuery = true)
    Lessor getByDwellingId(long dwellingId);
}
