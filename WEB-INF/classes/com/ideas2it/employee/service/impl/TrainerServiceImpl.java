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


public class TrainerServiceImpl implements TrainerService {

    private TrainerDao trainerDao = new TrainerDaoImpl();
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Constants.DATE_FORMAT);

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
    * @param dateOfJoining - Date, Trainer joined
    * @param dateOfBirth - Trainer Date of Birth
    * @param bloodGroup - Trainer Blood Group
    * @param qualification - Trainer qualification
    * @param trainingExperience - previous training Experience		
    * @return errors
    *         It returns List of Attributes, which failed validation 
    * @throws BadRequest
    *	      Exception will be thrown, If any details get Invalid
    **/
    public List<Attributes> addTrainer(final String name, final String address, final String mobileNumber,
				    final String email, final String dateOfJoining, final String dateOfBirth,
				    final String qualification, final String bloodGroup, final String trainingExperience) throws BadRequest {
	List<Attributes> errors = new ArrayList<>();
	StringBuilder errorMessage = new StringBuilder();

	String validName = null;
	if (!StringUtil.isValidName(name)) {
	    errors.add(Attributes.NAME);
	    errorMessage.append(ErrorMessage.NAME.errorMessage);
	} else {
	    validName = name;
	}

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
	//if (DateUtil.isValidDateFormat(dateOfJoining)) {
	    validDateOfJoining = LocalDate.parse(dateOfJoining);
	    if (DateUtil.computeDays(validDateOfJoining, LocalDate.now()) < 1) {
	    	errors.add(Attributes.DATE_OF_JOINING);
	    	errorMessage.append(ErrorMessage.DATE_OF_JOINING.errorMessage);
	    }
	/*} else {
	    errors.add(Attributes.DATE_OF_JOINING);
	    errorMessage.append(ErrorMessage.DATE_OF_JOINING.formatErrorMessage);
	}*/

	LocalDate validDateOfBirth = null;
	//if (DateUtil.isValidDateFormat(dateOfJoining)) {
	    validDateOfBirth = LocalDate.parse(dateOfBirth);
	    if (DateUtil.computePeriod(validDateOfBirth, LocalDate.now()) < 18) {
	    	errors.add(Attributes.DATE_OF_BIRTH);
	    	errorMessage.append(ErrorMessage.DATE_OF_BIRTH.errorMessage);
	    }
	/*} else {
	    errors.add(Attributes.DATE_OF_BIRTH);
	    errorMessage.append(ErrorMessage.DATE_OF_BIRTH.formatErrorMessage);
	}*/
	
	Qualification validQualification = new Qualification(qualification);

		Byte validTrainingExperience = null;
	try {
	    validTrainingExperience = Byte.valueOf(trainingExperience);
	} catch (NumberFormatException e) {
	    errors.add(Attributes.TRAINING_EXPERIENCE);
	    errorMessage.append(ErrorMessage.TRAINING_EXPERIENCE.errorMessage);
	}
        Role role = new Role("Trainer");

	if (errors.isEmpty()) {
	    Employee employee = new Employee(validName, address, validMobileNumber, validEmail, validDateOfJoining,
					     validDateOfBirth, bloodGroup, validQualification, role);
	    Trainer trainer = new Trainer(employee, validTrainingExperience);
	    trainerDao.insertTrainer(trainer);
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
	boolean isDeleted = false;
	if (trainerDao.deleteTrainerById(trainerId)) {
	    isDeleted = true;
	} else {
	    throw new TrainerNotFound(trainerId + " Not Found");
	}
	return isDeleted;
    }

    /**
    * <p>
    * This method is used to Validate the User Input and Modify the Trainer object using Trainer Id
    * </p>
    * @param trainer - trainer object to update
    * @param value - Data got from the User to Update
    * @param inputType - Column/Attribute Name
    * @return - It returns Boolean value, If Validation failed
    **/
    public boolean modifyTrainer(final Trainer trainer, final String value, final Attributes inputType) {
	boolean isValueValid = true;

	switch (inputType) {
	    case ADDRESS:
	        trainer.getEmployee().setAddress(value);
	        break;
	
	    case MOBILE_NUMBER:
	        Long mobileNumber;
	        if (!StringUtil.isValidMobileNumber(value)) {
		    isValueValid = false;
		    throw new BadRequest(2, "Invalid Mobile Number!, please Re-enter correctly");
	        } else {
	    	    mobileNumber = Long.valueOf(value);
		    trainer.getEmployee().setMobileNumber(mobileNumber);
	        }
	        break;

	    case EMAIL:
	        if (!StringUtil.isValidEmail(value)) {
		    isValueValid = false;
	    	    throw new BadRequest(3,"Invalid Email, It must End with ideas2it.com");
	        } else {
	    	    trainer.getEmployee().setEmail(value);
	        }
	        break;

	    case TRAINING_EXPERIENCE:
	        try {
		    Integer trainingExperience = Integer.valueOf(value);
		    trainer.setTrainingExperience(trainingExperience);
	        } catch (NumberFormatException e) {
		    isValueValid = false;
		    throw new BadRequest(4, "Invalid Training Experience");
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
    * 		- trainer object has to be passed to get updated
    * @return - It returns true/false
    **/
    public boolean modifyTrainerIntoDB(Trainer trainer) {
	return trainerDao.updateTrainer(trainer);
    }
}