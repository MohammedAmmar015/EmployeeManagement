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
    * This method is to Validate and add Trainee Details
    * </p>
    * @param trainee - object of trainee
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
    * @param trainersId - List of trainer Ids
    * @throws BadRequest
    *		It throws exceptions, If any data is Invalid
    * @return errors
    *         It returns List of Attributes, which failed validation 
    **/
    List<Integer> addOrModifyTrainee(final Trainee trainee, final String tempName, final String tempAddress, final String tempMobileNumber,
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

}