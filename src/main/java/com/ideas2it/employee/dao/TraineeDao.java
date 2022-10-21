package com.ideas2it.employee.dao;

import com.ideas2it.employee.models.Trainee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityResult;
import javax.persistence.FieldResult;
import javax.persistence.SqlResultSetMapping;
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
    /*@Query(value = "select te.course, te.batch_number, te.training_period, emp.id, emp.name, emp.address, emp.blood_group, emp.date_of_birth, emp.date_of_join, emp.email_id, emp.mobile_number from trainee te inner join employee as emp on emp.id = te.id " +
                    "inner join employee_relation er on er.trainee_id = te.id " +
                    "where er.trainer_id = ?1", nativeQuery = true)


     */
    /*@Query(
            value = "SELECT te.* from trainee te inner join employee_relation er on er.trainee_id = te.id where er.trainer_id = ?1",
            nativeQuery = true)*/
    @Query(value = " SELECT te.*, e.address, e.blood_group, e.date_of_birth, e.date_of_join, e.email_id, e.mobile_number, e.name from trainee te inner join employee_relation er on er.trainee_id = te.id inner join employee e on e.id = te.id where er.trainer_id = ?1",
            nativeQuery = true)

    //@Query(value = "SELECT * from Trainee te inner join employee_relation on te.id = trainee_id where trainer_id = ?1", nativeQuery = true)
    //@Query("select te from Trainee te")
    public List<Object[]> retreiveTraineesByTrainerId(int id);
}