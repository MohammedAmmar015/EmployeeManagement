/**
 *
 * TrainerService class - This is a Service class for Trainer,
 * To validate user input through Util classes 
 * @author Mohammed Ammar
 * @version 1.0 
 * @since 12/08/2022
 *
 **/
package com.ideas2it.employee.service.impl;

import com.ideas2it.employee.constant.Attributes;
import com.ideas2it.employee.constant.ErrorMessage;
import com.ideas2it.employee.dao.inter.TrainerDao;
import com.ideas2it.employee.exception.BadRequest;
import com.ideas2it.employee.exception.TrainerNotFound;
import com.ideas2it.employee.models.Employee;
import com.ideas2it.employee.models.Qualification;
import com.ideas2it.employee.models.Role;
import com.ideas2it.employee.models.Trainer;
import com.ideas2it.employee.service.inter.TrainerService;
import com.ideas2it.employee.utilities.DateUtil;
import com.ideas2it.employee.utilities.StringUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.spi.LoggerContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Component
public class TrainerServiceImpl implements TrainerService {

	@Autowired
    private TrainerDao trainerDao;
    private Logger logger = LogManager.getLogger(TrainerServiceImpl.class);

	/**
    * <p>
    * This method is to Validate and insert the Trainer details
    * </p>
    * @param trainer - trainer object

    * @return errors
    *         It returns List of Attributes, which failed validation 
    * @throws BadRequest
    *	      Exception will be thrown, If any details get Invalid
    **/


    public List<Attributes> addOrModifyTrainer(Trainer trainer) throws BadRequest {
	logger.info("Entered addOrModifyTrainer() method");
	List<Attributes> errors = new ArrayList<>();
	StringBuilder errorMessage = new StringBuilder();

	String validName = trainer.getEmployee().getName();
	if (!StringUtil.isValidName(validName)) {
		errors.add(Attributes.NAME);
		errorMessage.append(ErrorMessage.NAME.errorMessage);
	}

	Long validMobileNumber = trainer.getEmployee().getMobileNumber();
	if (!StringUtil.isValidMobileNumber(validMobileNumber.toString())) {
	    errors.add(Attributes.MOBILE_NUMBER);
	    errorMessage.append(ErrorMessage.MOBILE_NUMBER.errorMessage);
	}

	String validEmail = trainer.getEmployee().getEmail();
	if (!StringUtil.isValidEmail(validEmail)) {
	    errors.add(Attributes.EMAIL);
	    errorMessage.append(ErrorMessage.EMAIL.errorMessage);
	}

	LocalDate validDateOfJoining = trainer.getEmployee().getDateOfJoining();
	if (DateUtil.computeDays(validDateOfJoining, LocalDate.now()) < 1) {
	    errors.add(Attributes.DATE_OF_JOINING);
	    errorMessage.append(ErrorMessage.DATE_OF_JOINING.errorMessage);
	}

	LocalDate validDateOfBirth = trainer.getEmployee().getDateOfBirth();
	if (DateUtil.computePeriod(validDateOfBirth, LocalDate.now()) < 18) {
		errors.add(Attributes.DATE_OF_BIRTH);
		errorMessage.append(ErrorMessage.DATE_OF_BIRTH.errorMessage);
	}

	Role role = new Role("Trainer");
	trainer.getEmployee().setRole(role);
	if (errors.isEmpty()) {
		trainerDao.insertOrUpdateTrainer(trainer);
	} else {
	    errorMessage.append(errors.size()).append(" Errors Found");
	    errorMessage.append("\t\t\tPlease Re-enter the Trainer details correctly");
	    throw new BadRequest(errors, errorMessage.toString());
	}
	return errors;
    }

    /**
    * <p>
    * This method is used to get and return List of Trainers
    * </p>
    * @return - It returns List of Trainers
    **/
    public List<Trainer> getTrainers() {
	logger.info("Entered getTrainers() method");
	return trainerDao.retrieveTrainers();
    }	

    /**
    * <p>
    * This method is used to get and return One particular Trainer
    * Based on Employee Id
    * </p>
    * @param trainerId - Employee/Trainer Id
    * @return - It returns single Trainer
    **/
    public Trainer getTrainerById(final int trainerId) {
	logger.info("Entered getTrainerById() method");
	Trainer trainer = trainerDao.retrieveTrainerById(trainerId);
	if (trainer == null) {
	    throw new TrainerNotFound("Entered trainer id not Found");
	}
	return trainer;
    }

    /**
    * <p>
    * This method is used to remove Trainer object using Trainer Id
    * </p>
    * @param trainerId - Employee/Trainer Id
    * @return - It returns nothing
    * @throws TrainerNotFound
    * 		Exception will be thrown, If Trainer Not found
    **/
    public boolean removeTrainerById(final int trainerId) {
	logger.info("Entered removeTrainerById() method");
	boolean isDeleted = false;
	if (trainerDao.deleteTrainerById(trainerId)) {
	    isDeleted = true;
	} else {
	    throw new TrainerNotFound(trainerId + " Not Found");
	}
	return isDeleted;
    }
}