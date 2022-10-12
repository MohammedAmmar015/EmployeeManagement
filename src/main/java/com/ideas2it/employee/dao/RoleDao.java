package com.ideas2it.employee.dao;

import com.ideas2it.employee.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleDao extends JpaRepository<Role, Integer> {
    Optional<Role> findByDescription(String description);
}
