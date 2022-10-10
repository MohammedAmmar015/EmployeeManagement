package com.ideas2it.employee.dao.inter;

import com.ideas2it.employee.models.Trainer;

import java.util.List;


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
public interface TrainerDao {

    /**
     * <p>
     * This method is used to Insert or Update Trainer Details into Database using Hibernate
     * </p>
     *
     * @param trainer trainer object has to be passed to store
     **/
    void insertOrUpdateTrainer(Trainer trainer);


    /**
    * <p>
    * This is method is used to Retrieve all Trainers from the Database using Hibernate
    * </p>
    * @return - trainers
    *		 It returns all trainers from the List
    **/
    List<Trainer> retrieveTrainers();


    /**
    * <p>
    * This method is used to Delete Trainer Details from the Database using Hibernate
    * </p>
    * @param trainerId
    * 		Trainer id has to be passed to this method
    *  		to get deleted the Trainer Object
    **/
    boolean deleteTrainerById(int trainerId);


    /**
    * <p>
    * This method is used to Retrieve the particular Trainer Details from Database
    * Based on Trainer Id
    * </p>
    * @param trainerId
    *		Trainer id has to be passed to this method to retrieve
    * @return - trainer
    *		 This method will return trainer Object based on Trainer id
    **/
    Trainer retrieveTrainerById(int trainerId);

    List<Trainer> retrieveTrainersByIds(List<Integer> trainerIds);
}