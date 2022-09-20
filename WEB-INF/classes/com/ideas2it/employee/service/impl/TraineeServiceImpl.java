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

import com.ideas2it.employee.constant.Constants;
import com.ideas2it.employee.constant.ErrorMessage;
import com.ideas2it.employee.constant.Attributes;
import com.ideas2it.employee.utilities.StringUtil;
import com.ideas2it.employee.utilities.DateUtil;
import com.ideas2it.employee.dao.impl.TraineeDaoImpl;
import com.ideas2it.employee.dao.inter.TraineeDao;
import com.ideas2it.employee.models.Trainee;
import com.ideas2it.employee.models.Employee;
import com.ideas2it.employee.models.Role;
import com.ideas2it.employee.models.Qualification;
import com.ideas2it.employee.service.inter.TraineeService;
import com.ideas2it.employee.exception.BadRequest;
import com.ideas2it.employee.exception.TraineeNotFound;
import java.util.InputMismatchException;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TraineeServiceImpl implements TraineeService {
    private TraineeDao traineeDao = new TraineeDaoImpl();
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Constants.DATE_FORMAT);

    /**
    * <p>
    * This method is to Validate the Trainee details
    * If all data is Valid. Then, Create object for that
    * and Add it to the List
    * </p>
    * @param name - Trainee Name
    * @param address - Trainee Address
    * @param mobileNumber - Trainee Mobile Number
    * @param email - Trainee Email
    * @param dateOfJoining - Date, Trainee joined
    * @param dateOfBirth - Trainee Date of Birth
    * @param bloodGroup - Trainee Blood Group
    * @param qualification - Trainee qualification
    * @param trainingPeriod - Trainee training Period(In Months)
    * @param course - Course, Trainee undergoing
    * @param batchNumber - Trainee Batch Number
    * @param trainersId - List of trainers id
    * @throws BadRequest
    *		It throws exceptions, If any data is Invalid
    * @return errors
    *         It returns List of Attributes, which failed validation 
    **/
    public List<Attributes> addTrainee(final String name, final String address, final String mobileNumber,
				    final String email, final String dateOfJoining, final String dateOfBirth,
				    final String qualification, final String bloodGroup, final String trainingPeriod, 
				    final String course, final String batchNumber, final List<String> trainersId) throws BadRequest {
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
	//if (DateUtil.isValidDateFormat(dateOfBirth)) {
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
	Byte validTrainingPeriod = null;
	try {
	    validTrainingPeriod = Byte.valueOf(trainingPeriod);
	} catch (NumberFormatException e) {
	    errors.add(Attributes.TRAINING_PERIOD);
	    errorMessage.append(ErrorMessage.TRAINING_PERIOD.errorMessage);
	}

	Byte validBatchNumber = null;
	try {
	    validBatchNumber = Byte.valueOf(batchNumber);
	} catch (NumberFormatException e) {
	    errors.add(Attributes.BATCH_NUMBER);
	    errorMessage.append(ErrorMessage.BATCH_NUMBER.errorMessage);
	}
	
	List<Integer> validTrainersId = new ArrayList<>();
	for (String trainerId : trainersId) {
	    
	    validTrainersId.add(Integer.valueOf(trainerId));
	}
	
	if (errors.isEmpty()) {
	    Role role = new Role("Trainee");
	    Employee employee = new Employee(validName, address, validMobileNumber, validEmail, validDateOfJoining,
					     validDateOfBirth, bloodGroup, validQualification, role);
	    Trainee trainee = new Trainee(employee, validTrainingPeriod, course, validBatchNumber, validTrainersId);
	    traineeDao.insertTrainee(trainee);
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
	if (!traineeDao.deleteTraineeById(traineeId)) {
	    throw new TraineeNotFound(traineeId + " Not Found");
	}
	return true;
    }

    /**
    * <p>
    * This method is used to Validate the User Input and Modify the Trainee object using Trainee Id
    * </p>
    * @param trainee - trainee object to update
    * @param value - Data got from the User to Update
    * @param inputType - Column/Attribute Name
    * @return - It returns Boolean value, If Validation failed
    **/
    public boolean modifyTrainee(final Trainee trainee, final String value, final Attributes inputType) {
	boolean isValueValid = true;
	switch (inputType) {
	    case ADDRESS:
	        trainee.getEmployee().setAddress(value);
	        break;
	
	    case MOBILE_NUMBER:
	        Long mobileNumber;
	        if (!StringUtil.isValidMobileNumber(value)) {
		    isValueValid = false;
		    throw new BadRequest(2, "Invalid Mobile Number!, please Re-enter correctly");
		    
	        } else {
	    	    mobileNumber = Long.valueOf(value);
		    trainee.getEmployee().setMobileNumber(mobileNumber);
	        }
	        break;

	    case EMAIL:
	        if (!StringUtil.isValidEmail(value)) {
		    isValueValid = false;
	    	    throw new BadRequest(3,"Invalid Email, It must End with ideas2it.com");
	        } else {
	    	    trainee.getEmployee().setEmail(value);
	        }
	        break;

	    case COURSE:
	        trainee.setCourse(value);
	        break;
	} 
	return isValueValid;
    }

    /**
    * <p>
    * This method is used to Validate the List trainers Name and Modify the Trainee object using Trainee Id
    * </p>
    * @param trainee - trainee object to update
    * @param trainersId- List of trainers id to update
    * @param inputType - Column/Attribute Name
    * @return - It returns Nothing
    **/
    public boolean modifyTrainerNames(Trainee trainee, List<String> trainersId, Attributes inputType) {
	boolean isValid = true;
	List<Integer> validTrainersId = new ArrayList<>();
	for (String trainerId : trainersId) {
	    try {
	        validTrainersId.add(Integer.valueOf(trainerId));
	    } catch (InputMismatchException e) {
		isValid = false;
	    }
	}	
	trainee.setTrainersId(validTrainersId);
	return isValid;
    }

    /**
    * <p>
    * This method is used to Update trainee Details into Database using Hibernate
    * </p>
    * @param trainee
    * 		- trainee object has to be passed to get updated
    * @return - It returns nothing
    **/
    public boolean modifyTraineeIntoDB(Trainee trainee) {
	return traineeDao.updateTrainee(trainee);
    } 
}