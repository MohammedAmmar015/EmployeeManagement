package com.ideas2it.employee.dao;

import com.ideas2it.employee.models.Qualification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QualificationDao extends JpaRepository<Qualification, Integer> {

    Optional<Qualification> findByDescription(String description);
}
