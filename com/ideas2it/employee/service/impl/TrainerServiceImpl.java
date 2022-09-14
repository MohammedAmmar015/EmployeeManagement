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

import com.ideas2it.employee.constant.Constants;
import com.ideas2it.employee.constant.BloodGroup;
import com.ideas2it.employee.constant.ErrorMessage;
import com.ideas2it.employee.constant.Attributes;
import com.ideas2it.employee.utilities.StringUtil;
import com.ideas2it.employee.utilities.DateUtil;
import com.ideas2it.employee.dao.impl.TrainerDaoImpl;
import com.ideas2it.employee.dao.inter.TrainerDao;
import com.ideas2it.employee.models.Trainer;
import com.ideas2it.employee.models.Employee;
import com.ideas2it.employee.models.Role;
import com.ideas2it.employee.models.Qualification;
import com.ideas2it.employee.service.inter.TrainerService;
import com.ideas2it.employee.exception.TrainerNotFound;
import com.ideas2it.employee.exception.BadRequest;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class TrainerServiceImpl implements TrainerService {

    private TrainerDao trainerDao = new TrainerDaoImpl();
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Constants.DATE_FORMAT);
    private static Logger logger = LogManager.getLogger(TrainerServiceImpl.class);

    /**
    * <p>
    * This method is to Validate the Trainer details
    * If all data is Valid. Then, Create object for that
    * and Add it to the List 
    * </p>
    * @param name - Trainer Name
    * @param address - Trainer Address
    * @param mobileNumber - Trainer Mobile Number
    * @param email - Trainer Email
    * @param dateOfjoining - Date, Trainer joined
    * @param dateOfBirth - Trainer Date of Birth
    * @param bloodGroup - Trainer Blood Group
    * @param qualification - Trainer qualification
    * @param trainingExperience - previous training Experience
    * @param role - It has Role Description - "Trainer" 		
    * @return errors
    *         It returns List of Attributes, which failed validation 
    * @throws BadrequestException 
    *	      Exception will be thrown, If any details get Invalid
    **/
    public List<Attributes> addTrainer(final String name, final String address, final String mobileNumber,
				    final String email, final String dateOfJoining, final String dateOfBirth,
				    final String qualification, final String bloodGroup, final String trainingExperience, final Role role) throws BadRequest {
	List<Attributes> errors = new ArrayList<>();
	StringBuilder errorMessage = new StringBuilder();

	String validName = null;
	if (!StringUtil.isValidName(name)) {
	    errors.add(Attributes.NAME);
	    errorMessage.append(ErrorMessage.NAME.errorMessage);
	} else {
	    validName = name;
	}

	String validAddress = address;
	
	Long validMobileNumber = null;
	if (!StringUtil.isValidMobileNumber(mobileNumber)) {
	    errors.add(Attributes.MOBILE_NUMBER);
	    errorMessage.append(ErrorMessage.MOBILE_NUMBER.errorMessage);
	} else {
	    validMobileNumber = Long.valueOf(mobileNumber);
	}

	String validEmail = null;
	if (!StringUtil.isValidEmail(email)) {
	    errors.add(Attributes.EMAIL);
	    errorMessage.append(ErrorMessage.EMAIL.errorMessage);
	} else {
	    validEmail = email;
	}
	
	LocalDate validDateOfJoining = null;
	if (DateUtil.isValidDateFormat(dateOfJoining)) {
	    validDateOfJoining = LocalDate.parse(dateOfJoining, formatter);
	    if (DateUtil.computeDays(validDateOfJoining, LocalDate.now()) < 1) {
	    	errors.add(Attributes.DATE_OF_JOINING);
	    	errorMessage.append(ErrorMessage.DATE_OF_JOINING.errorMessage);
	    }
	} else {
	    errors.add(Attributes.DATE_OF_JOINING);
	    errorMessage.append(ErrorMessage.DATE_OF_JOINING.formatErrorMessage);
	}

	LocalDate validDateOfBirth = null;
	if (DateUtil.isValidDateFormat(dateOfJoining)) {
	    validDateOfBirth = LocalDate.parse(dateOfBirth, formatter);
	    if (DateUtil.computePeriod(validDateOfBirth, LocalDate.now()) < 18) {
	    	errors.add(Attributes.DATE_OF_BIRTH);
	    	errorMessage.append(ErrorMessage.DATE_OF_BIRTH.errorMessage);
	    }
	} else {
	    errors.add(Attributes.DATE_OF_BIRTH);
	    errorMessage.append(ErrorMessage.DATE_OF_BIRTH.formatErrorMessage);
	}
	
	Qualification validQualification = new Qualification(qualification);
	String validBloodGroup = bloodGroup;

	Byte validTrainingExperience = null;
	try {
	    validTrainingExperience = Byte.valueOf(trainingExperience);
	} catch (NumberFormatException e) {
	    errors.add(Attributes.TRAINING_EXPERIENCE);
	    errorMessage.append(ErrorMessage.TRAINING_EXPERIENCE.errorMessage);
	}

	if (errors.isEmpty()) {
	    Employee employee = new Employee(validName, validAddress, validMobileNumber, validEmail, validDateOfJoining, 
					     validDateOfBirth, validBloodGroup, validQualification, role);
	    Trainer trainer = new Trainer(employee, validTrainingExperience);
	    if (trainerDao.insertTrainer(trainer)) {
		logger.info("Trainer details Added Successfully");
	    } else {
		logger.error("There are Some issue in Adding Trainer Details");
	    }
	} else {
	    logger.warn(errors.size() + " Errors Found");
	    logger.warn("\t\t\tPlease Re-enter the Trainer details correctly");
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
	List<Trainer> trainers = trainerDao.retriveTrainers();
	if (trainers.isEmpty()) {
	    logger.error("No Trainer Found To Display");
	}
	return trainers;
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
	Trainer trainer = trainerDao.retriveTrainerById(trainerId);
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
    public void removeTrainerById(final int trainerId) {
	if (trainerDao.deleteTrainerById(trainerId)) {
	    logger.info("Trainer Deleted Successfully");
	} else {
	    throw new TrainerNotFound(trainerId + " Not Found");
	}
    }

    /**
    * <p>
    * This method is used to Validate the User Input and Modify the Trainer object using Trainer Id
    * </p>
    * @param trainerId - Employee/Trainer Id
    * @param value - Data got from the User to Update
    * @param inputType - Column/Attribute Name
    * @return - It returns Boolean value, If Validation failed
    **/
    public boolean modifyTrainer(final Trainer trainer, final String value, final Attributes inputType) {
	boolean isValueValid = true;
   	int updatedCount = 0;

	switch (inputType) {
	    case ADDRESS:
	        trainer.getEmployee().setAddress(value);
	        break;
	
	    case MOBILE_NUMBER:
	        Long mobileNumber = null;
	        if (!StringUtil.isValidMobileNumber(value)) {
	    	    logger.warn("Invalid Mobile Number, It must have 10 digits");
		    isValueValid = false;
	        } else {
	    	    mobileNumber = Long.valueOf(value);
		    trainer.getEmployee().setMobileNumber(mobileNumber);
	        }
	        break;

	    case EMAIL:
	        if (!StringUtil.isValidEmail(value)) {
		    isValueValid = false;
	    	    logger.warn("Invalid Email, It must End with ideas2it.com");
	        } else {
	    	    trainer.getEmployee().setEmail(value);
	        }
	        break;

	    case TRAINING_EXPERIENCE:
	        try {
		    Integer trainingExperience = Integer.valueOf(value);
		    trainer.setTrainingExperience(trainingExperience);
	        } catch (NumberFormatException e) {
		    logger.warn("Invalid Number Of Trainees");
		    isValueValid = false;
	        }
	        break;
	}   
	return isValueValid;
    }

    /**
    * <p>
    * This method is used to Update trainer Details into Database using Hibernate
    * </p>
    * @param trainer
    * 		- trainer object has to be passesd to get updated
    * @return - It returns nothing
    **/
    public void modifyTrainerIntoDB(Trainer trainer) {
	if (trainerDao.updateTrainer(trainer)) {
	    logger.info("Trainer Details Updated Successfully");
	} else {
	    logger.error("Failed to Update Trainer Details");
	}
    }
}