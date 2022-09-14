package com.ideas2it.employee.service.inter;
import java.util.List;
import com.ideas2it.employee.constant.Attributes;
import com.ideas2it.employee.constant.BloodGroup;
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
    * This method is to Validate the Trainer details
    * If all data is Valid. Then, Create object for that
    * and Add it to the List 
    * </p>
    * @param tempName - Trainer Name
    * @param tempAddress - Trainer Address
    * @param tempMobileNumber - Trainer Mobile Number
    * @param tempEmail - Trainer Email
    * @param tempDateOfjoining - Date, Trainer joined
    * @param tempDateOfBirth - Trainer Date of Birth
    * @param tempBloodGroup - Trainer Blood Group
    * @param tempQualification - Trainer qualification
    * @param tempNumberOfTrainers - Number of Trainees under this trainer		
    * @return errors
    *         It returns List of Attributes, which failed validation 
    * @throws BadrequestException 
    *	      Exception will be thrown, If any details get Invalid
    **/
    public List<Attributes> addTrainer(final String tempName, final String tempAddress, final String tempMobileNumber,
				       final String tempEmail, final String tempDateOfJoining, final String tempDateOfBirth,
				       final String tempQualification, final String tempBloodGroup, final String trainingExperience, final Role role) 
				       throws BadRequest;


    /**
    * <p>
    * This method is used to get and return List of Trainers
    * </p>
    * @return - It returns List of Trainers
    **/
    public List<Trainer> getTrainers();


    /**
    * <p>
    * This method is used to get and return One particular Trainer
    * Based on Employee Id
    * </p>
    * @param trainerId - Employee/Trainer Id
    * @return - It returns single Trainer
    **/
    public Trainer getTrainerById(final int trainerId);


    /**
    * <p>
    * This method is used to remove Trainer object using Trainer Id
    * </p>
    * @param trainerId - Employee/Trainer Id
    * @return - It returns nothing
    * @throws TrainerNotFound
    * 		Exception will be thrown, If Trainer Not found
    **/
    public void removeTrainerById(final int trainerId) throws TrainerNotFound;


    /**
    * <p>
    * This method is used to Validate the User Input and Modify the Trainer object using Trainer Id
    * </p>
    * @param trainerId - Employee/Trainer Id
    * @param value - Data got from the User to Update
    * @param inputType - Column/Attribute Name
    * @return - It returns Boolean value, If Validation failed
    **/
    public boolean modifyTrainer(final Trainer trainer, final String value, final Attributes inputType);

    /**
    * <p>
    * This method is used to Update trainer Details into Database using Hibernate
    * </p>
    * @param trainer
    * 		- trainer object has to be passesd to get updated
    * @return - It returns nothing
    **/
    public void modifyTrainerIntoDB(Trainer trainer);

}