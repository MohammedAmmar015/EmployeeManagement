package com.ideas2it.employee.service.inter;
import java.util.List;
import com.ideas2it.employee.constant.Attributes;
import com.ideas2it.employee.models.Trainer;
import com.ideas2it.employee.models.Role;
import com.ideas2it.employee.exception.TrainerNotFound;
import com.ideas2it.employee.exception.BadRequest;

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
    * @param trainer - trainer object
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
    List<Attributes> addOrModifyTrainer(Trainer trainer, final String tempName, final String tempAddress, final String tempMobileNumber,
				       final String tempEmail, final String tempDateOfJoining, final String tempDateOfBirth,
				       final String tempQualification, final String tempBloodGroup, final String trainingExperience) 
				       throws BadRequest;


    /**
    * <p>
    * This method is used to get and return List of Trainers
    * </p>
    * @return - It returns List of Trainers
    **/
    List<Trainer> getTrainers();


    /**
    * <p>
    * This method is used to get and return One particular Trainer
    * Based on Employee Id
    * </p>
    * @param trainerId - Employee/Trainer Id
    * @return - It returns single Trainer
    **/
    Trainer getTrainerById(final int trainerId);


    /**
    * <p>
    * This method is used to remove Trainer object using Trainer Id
    * </p>
    * @param trainerId - Employee/Trainer Id
    * @return - It returns nothing
    * @throws TrainerNotFound
    * 		Exception will be thrown, If Trainer Not found
    **/
    boolean removeTrainerById(final int trainerId) throws TrainerNotFound;
}