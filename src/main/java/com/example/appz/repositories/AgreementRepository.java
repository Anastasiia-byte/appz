package com.example.appz.repositories;

import com.example.appz.entities.Agreement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AgreementRepository extends JpaRepository<Agreement, Long> {
    List<Agreement> findAllByCompleteIs(boolean complete);
}
