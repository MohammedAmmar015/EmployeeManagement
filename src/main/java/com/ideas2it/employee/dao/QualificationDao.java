package com.ideas2it.employee.dao;

import com.ideas2it.employee.models.Qualification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface QualificationDao extends JpaRepository<Qualification, Integer> {

    Optional<Qualification> findByDescription(String description);
}
