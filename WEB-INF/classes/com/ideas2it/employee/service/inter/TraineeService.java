package com.ideas2it.employee.service.inter;
import java.util.List;
import com.ideas2it.employee.constant.Attributes;
import com.ideas2it.employee.models.Trainee;
import com.ideas2it.employee.models.Role;
import com.ideas2it.employee.exception.BadRequest;
import com.ideas2it.employee.exception.TraineeNotFound;

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
    * This method is to Validate the Trainee details
    * If all data is Valid. Then, Create object for that
    * and Add it to the List
    * </p>
    * @param tempName - Trainee Name
    * @param tempAddress - Trainee Address
    * @param tempMobileNumber - Trainee Mobile Number
    * @param tempEmail - Trainee Email
    * @param tempDateOfJoining - Date, Trainee joined
    * @param tempDateOfBirth - Trainee Date of Birth
    * @param tempBloodGroup - Trainee Blood Group
    * @param tempQualification - Trainee qualification
    * @param tempTrainingPeriod - Trainee training Period(In Months)
    * @param tempCourse - Course, Trainee undergoing
    * @param tempBatchNumber - Trainee Batch Number
    * @throws BadRequest
    *		It throws exceptions, If any data is Invalid
    * @return errors
    *         It returns List of Attributes, which failed validation 
    **/
    List<Attributes> addTrainee(final String tempName, final String tempAddress, final String tempMobileNumber,
				       final String tempEmail, final String tempDateOfJoining, final String tempDateOfBirth,
				       final String tempQualification, final String tempBloodGroup, final String tempTrainingPeriod, 
				       final String tempCourse, final String tempBatchNumber, final List<String> trainersId) throws BadRequest;

    /**
    * <p>
    * This method is used to get and return List of Trainees
    * </p>
    * @return - It returns List of Trainees
    **/
    List<Trainee> getTrainees();


    /**
    * <p>
    * This method is used to get and return One particular Trainee
    * Based on Employee Id
    * </p>
    * @param traineeId - Employee/Trainee Id
    * @return - It returns single Trainee
    **/
    Trainee getTraineeById(final int traineeId);


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
    * <p>
    * This method is used to Validate the User Input and Modify the Trainee object using Trainee Id
    * </p>
    * @param trainee - trainee object to update
    * @param value - Data got from the User to Update
    * @param inputType - Column/Attribute Name
    * @return - It returns Boolean value, If Validation failed
    **/
    boolean modifyTrainee(final Trainee trainee, final String value, final Attributes inputType);


    /**
    * <p>
    * This method is used to Validate the List trainers Name and Modify the Trainee object using Trainee Id
    * </p>
    * @param trainee- trainee object to update
    * @param trainersName - Data got from the User to Update
    * @param inputType - Column/Attribute Name
    * @return - It returns boolean value, true/false
    **/
    boolean modifyTrainerNames(Trainee trainee, List<String> trainersName, Attributes inputType);

    /**
    * <p>
    * This method is used to Update trainee Details into Database using Hibernate
    * </p>
    * @param trainee
    * 		- trainee object has to be passed to get updated
    * @return - It returns nothing
    **/
    boolean modifyTraineeIntoDB(Trainee trainee);

}