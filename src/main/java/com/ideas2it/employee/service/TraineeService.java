package com.ideas2it.employee.service;

import com.ideas2it.employee.dto.TraineeDto;
import com.ideas2it.employee.exception.BadRequest;
import com.ideas2it.employee.exception.TraineeNotFound;

import java.util.List;

/**
 * <p>
 * This is Interface for TraineeService class
 * It has methods to Validate the details 
 * To add, get, modify and remove
 * </p>
 * @author Mohammed Ammar
 * @version 1.0 
 * @since 12/08/2022
 *
 **/
public interface TraineeService {

    /**
    * <p>
    * This method is to Validate and add Trainee Details
    * </p>
    * @param traineeDto - object of trainee
    * @throws BadRequest
    *		It throws exceptions, If any data is Invalid
    * @return id
    *         it returns inserted  trainee id
    **/
    int addOrModifyTrainee(final TraineeDto traineeDto) throws BadRequest;

    /**
    * <p>
    * This method is used to get and return List of Trainees
    * </p>
    * @return - It returns List of Trainees
    **/
    List<TraineeDto> getTrainees();


    /**
    * <p>
    * This method is used to get and return One particular Trainee
    * Based on Employee Id
    * </p>
    * @param traineeId - Employee/Trainee Id
    * @return - It returns single Trainee
    **/
    TraineeDto getTraineeById(final int traineeId);


    /**
    * <p>
    * This method is used to remove Trainee object using Trainee Id
    * </p>
    * @param traineeId - Employee/Trainee Id
    * @throws TraineeNotFound
    *   	Exception will e thrown, When given id Not found
    * @return - It returns nothing
    **/
    boolean removeTraineeById(final int traineeId);


    /**
     *  This method is used to retrieve Trainees of Particular Trainer
     *  using their Trainer Id
     * @param trainerId
     * @return
     */
    List<TraineeDto> getTraineesByTrainerId(int trainerId);
}