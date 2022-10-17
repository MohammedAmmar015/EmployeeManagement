package com.ideas2it.employee.dao;

import com.ideas2it.employee.models.Qualification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * This is Qualification Repositiory
 * to do CRUD operation on qualification table
 * @author Mohammed Ammar
 * @version 1.0
 * @since 17/10/2022
 */
@Repository
public interface QualificationDao extends JpaRepository<Qualification, Integer> {

    Optional<Qualification> findByDescription(String description);
}
