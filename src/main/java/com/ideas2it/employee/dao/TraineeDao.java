package com.ideas2it.employee.dao;

import com.ideas2it.employee.models.Trainee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


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
@Repository
public interface TraineeDao extends JpaRepository<Trainee, Integer> {
    @Query("Select te from Trainee te inner join te.trainers er where er.id = ?1")
    List<Trainee> retreiveTraineesByTrainerId(int id);
}
