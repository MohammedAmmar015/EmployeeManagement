package com.ideas2it.employee.dao.inter;

import com.ideas2it.employee.models.Trainee;

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
public interface TraineeDao {

    /**
     * <p>
     * This method is used to Insert or Update Trainee details into Database
     * Using Hibernate
     * </p>
     *
     * @param trainee trainee object has to be passed to store
     **/
    void insertOrUpdateTrainee(Trainee trainee);


    /**
    * <p>
    * This is method is used to Retrieve all Trainees from the Database
    * Using Hibernate 
    * </p>
    * @return - trainees
    *		 It returns all trainees from the List
    **/
    List<Trainee> retrieveTrainees();


    /**
    * <p>
    * This method is used to Delete Trainee Details from the Database
    * Using Hibernate
    * </p>
    * @param traineeId
    * 		id of trainee has to be passed to this method
    *  		to get deleted the Trainee Object
    **/
    boolean deleteTraineeById(int traineeId);


    /**
    * <p>
    * This method is used to Retrieve the particular Trainee Details from Database
    * Based on Trainee Id, Using Hibernate
    * </p>
    * @param traineeId
    *		Trainee id has to be passed to this method to retrieve
    * @return trainee
    *		 This method will return trainee Object based on Trainee id
    **/
    Trainee retrieveTraineeById(int traineeId);
}