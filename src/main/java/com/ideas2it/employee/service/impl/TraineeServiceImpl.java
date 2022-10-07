/**
* <p>
* TraineeService class - This is a Service class for Trainee,
* To validate user input through Util classes 
* It has methods to Validate the details 
* To add, get, modify and remove
* </p>
* @author Mohammed Ammar
* @version 1.0 
* @since 12/08/2022
*
**/
package com.ideas2it.employee.service.impl;

import com.ideas2it.employee.constant.Attributes;
import com.ideas2it.employee.constant.ErrorMessage;
import com.ideas2it.employee.dao.inter.TraineeDao;
import com.ideas2it.employee.exception.BadRequest;
import com.ideas2it.employee.exception.TraineeNotFound;
import com.ideas2it.employee.models.*;
import com.ideas2it.employee.service.inter.TraineeService;
import com.ideas2it.employee.service.inter.TrainerService;
import com.ideas2it.employee.utilities.DateUtil;
import com.ideas2it.employee.utilities.StringUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class TraineeServiceImpl implements TraineeService {
	@Autowired
    private TrainerService trainerService;

	@Autowired
    private TraineeDao traineeDao;
    private Logger logger = LogManager.getLogger(TraineeServiceImpl.class);

	/**
    * <p>
    * This method is to Validate and add Trainee Details
    * </p>
    * @param trainee - object of trainee
    * @throws BadRequest
    *		It throws exceptions, If any data is Invalid
    * @return errors
    *         It returns List of Attributes, which failed validation 
    **/
    public List<Attributes> addOrModifyTrainee(Trainee trainee) throws BadRequest {
  	logger.info("Entered addOrModifyTrainee() method");
	List<Attributes> errors = new ArrayList<>();
	StringBuilder errorMessage = new StringBuilder();

	String validName = trainee.getEmployee().getName();
	if (!StringUtil.isValidName(validName)) {
	    errors.add(Attributes.NAME);
	    errorMessage.append(ErrorMessage.NAME.errorMessage);
	}

	Long validMobileNumber = trainee.getEmployee().getMobileNumber();
	if (!StringUtil.isValidMobileNumber(validMobileNumber.toString())) {
	    errors.add(Attributes.MOBILE_NUMBER);
	    errorMessage.append(ErrorMessage.MOBILE_NUMBER.errorMessage);
	}

	String validEmail = trainee.getEmployee().getEmail();
	if (!StringUtil.isValidEmail(validEmail)) {
	    errors.add(Attributes.EMAIL);
	    errorMessage.append(ErrorMessage.EMAIL.errorMessage);
	}
	
	LocalDate validDateOfJoining = trainee.getEmployee().getDateOfJoining();
	if (DateUtil.computeDays(validDateOfJoining, LocalDate.now()) < 1) {
	    errors.add(Attributes.DATE_OF_JOINING);
	    errorMessage.append(ErrorMessage.DATE_OF_JOINING.errorMessage);
	}

	LocalDate validDateOfBirth = trainee.getEmployee().getDateOfBirth();
	if (DateUtil.computePeriod(validDateOfBirth, LocalDate.now()) < 18) {
	    errors.add(Attributes.DATE_OF_BIRTH);
	    errorMessage.append(ErrorMessage.DATE_OF_BIRTH.errorMessage);
	}

	List<Trainer> trainers = trainerService.getTrainers();
	Set<Trainer> validTrainers = new HashSet<>();
	for (Integer trainerId : trainee.getTrainerIds()) {
	    for (Trainer trainer : trainers) {
	        if (trainerId == trainer.getEmployee().getId()) {
				validTrainers.add(trainer);
			}
	    }
	}
	trainee.setTrainers(validTrainers);
	Role role = new Role("Trainee");
	trainee.getEmployee().setRole(role);
	if (errors.isEmpty()) {
	    traineeDao.insertOrUpdateTrainee(trainee);
	} else {
	    errorMessage.append("\t\t\tPlease Re-enter the Trainee details correctly");
	    errorMessage.append(errors.size()).append(" Errors Found");
	    throw new BadRequest(errors, errorMessage.toString());
	}
	return errors;
    }
    
    /**
    * <p>
    * This method is used to get and return List of Trainees
    * </p>
    * @return - It returns List of Trainees
    **/
    public List<Trainee> getTrainees() {
  	logger.info("Entered getTrainees() method");
	return traineeDao.retrieveTrainees();
    }	

    /**
    * <p>
    * This method is used to get and return One particular Trainee
    * Based on Employee Id
    * </p>
    * @param traineeId - Employee/Trainee Id
    * @return - It returns single Trainee
    **/
    public Trainee getTraineeById(final int traineeId) {
  	logger.info("Entered getTraineeById() method");
	return traineeDao.retrieveTraineeById(traineeId);
    }
	
    /**
    * <p>
    * This method is used to remove Trainee object using Trainee Id
    * </p>
    * @param traineeId - Employee/Trainee Id
    * @throws TraineeNotFound
    *   	Exception will e thrown, When given id Not found
    * @return - It returns nothing
    **/
    public boolean removeTraineeById(final int traineeId) {
  	logger.info("Entered removeTraineeById() method");
	if (!traineeDao.deleteTraineeById(traineeId)) {
	    throw new TraineeNotFound(traineeId + " Not Found");
	}
	return true;
    }
}