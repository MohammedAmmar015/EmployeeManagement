package com.ideas2it.employee.service;

import com.ideas2it.employee.constant.Attributes;
import com.ideas2it.employee.dto.TrainerDto;
import com.ideas2it.employee.exception.BadRequest;
import com.ideas2it.employee.exception.TrainerNotFound;
import com.ideas2it.employee.models.Trainer;

import java.util.List;

/**
 * <p>
 * This is Interface for TrainerService class
 * It has methods to Validate the details 
 * To add, get, modify and remove
 * </p>
 * @author Mohammed Ammar
 * @version 1.0 
 * @since 12/08/2022
 *
 **/
public interface TrainerService {

    /**
    * <p>
    * This method is to Validate and insert the Trainer details
    * </p>
    * @param trainerDto - trainer object
    * @return id
    *         It returns inserted trainer id
    * @throws BadRequest
    *	      Exception will be thrown, If any details get Invalid
    **/
    int addOrModifyTrainer(TrainerDto trainerDto)
				       throws BadRequest;


    /**
    * <p>
    * This method is used to get and return List of Trainers
    * </p>
    * @return - It returns List of Trainers
    **/
    List<TrainerDto> getTrainers();


    /**
    * <p>
    * This method is used to get and return One particular Trainer
    * Based on Employee Id
    * </p>
    * @param trainerId - Employee/Trainer Id
    * @return - It returns single Trainer
    **/
    TrainerDto getTrainerById(final int trainerId);


    List<Trainer> getTrainersByIds(List<Integer> trainerIds);

    /**
    * <p>
    * This method is used to remove Trainer object using Trainer Id
    * </p>
    * @param trainerId - Employee/Trainer Id
    * @return - It returns nothing
    * @throws TrainerNotFound
    * 		Exception will be thrown, If Trainer Not found
    **/
    boolean removeTrainerById(final int trainerId);
}