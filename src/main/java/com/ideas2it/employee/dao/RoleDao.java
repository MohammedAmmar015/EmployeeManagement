package com.ideas2it.employee.dao;

import com.ideas2it.employee.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * This is Role Repository
 * to do CRUD Operation on Role Table
 * @author Mohammed Ammar
 * @version 1.0
 * @since 17/10/2022

 */
@Repository
public interface RoleDao extends JpaRepository<Role, Integer> {
    Optional<Role> findByDescription(String description);
}
