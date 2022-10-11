package com.ideas2it.employee.dao;

import com.ideas2it.employee.models.Trainee;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * <p>
 * This is Interface for TraineeDao Class, which contains methods,
 * To do CRUD operations
 * Insert, Retrieve, Update, Delete
 * </p>
 * @author Mohammed Ammar
 * @version 1.0 
 * @since 12/08/2022
 *
 **/
public interface TraineeDao extends JpaRepository<Trainee, Integer> {

}