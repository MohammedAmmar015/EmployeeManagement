package com.ideas2it.employee.dao;

import com.ideas2it.employee.models.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;



/**
 * <p>
 * This is Interface for TrainerDao class which contains methods,
 * To do CRUD operations on Database
 * Insert, Retrieve, Update, Delete
 * </p>
 * @author Mohammed Ammar
 * @version 1.0 
 * @since 12/08/2022
 *
 **/
public interface TrainerDao extends JpaRepository<Trainer, Integer> {

}