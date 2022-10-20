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
    //@Query(value = "select te.*, emp.* from trainee te inner join employee as emp on emp.id = te.id " +
      //              "inner join employee_relation er on er.trainee_id = te.id " +
        //            "where er.trainer_id = ?1", nativeQuery = true)
    @Query(value = "SELECT id, batch_number from employee te inner join employee_relation on te.id = trainee_id where trainer_id = ?1", nativeQuery = true)
    //@Query("select te from Trainee te")
    List<Trainee> retreiveTraineesByTrainerId(int trainerId);
}