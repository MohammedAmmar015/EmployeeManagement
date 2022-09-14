package com.ideas2it.employee.dao.inter;
import java.util.List;
import com.ideas2it.employee.models.Trainer;
import com.ideas2it.employee.constant.Attributes;


/**
 * <p>
 * This is Interface for TrainerDao class which contains methods,
 * To do CRUD operations on Database
 * Insert, Retrive, Update, Delete
 * </p>
 * @author Mohammed Ammar
 * @version 1.0 
 * @since 12/08/2022
 *
 **/
public interface TrainerDao {

    /**
    * <p>
    * This method is used to Insert Trainer Details into Database using Hibernate
    * </p>
    * @param - trainer
    *		trainer object has to be passed to store
    * @return - isTrainerAdded
    *		 it returns boolean value
    **/
    public boolean insertTrainer(Trainer trainer);


    /**
    * <p>
    * This is method is used to Retrive all Trainers from the Database using Hibernate
    * </p>
    * @return - trainers
    *		 It returns all trainers from the List
    **/
    public List<Trainer> retriveTrainers();


    /**
    * <p>
    * This method is used to Delete Trainer Details from the Database using Hibernate
    * </p>
    * @param - trainerId
    * 		Trainer Id has to be passed to this method
    *  		to get deleted the Trainer Object
    **/
    public boolean deleteTrainerById(int traineeId);


    /**
    * <p>
    * This method is used to Retrive the particular Trainer Details from Database
    * Based on Trainer Id
    * </p>
    * @param - trainerId
    *		Trainer Id has to be passed to this method to retrive
    * @return - trainer
    *		 This method will return trainer Object based on Trainer Id
    **/
    public Trainer retriveTrainerById(int traineeId);



    /**
    * <p>
    * This method is used to Update Trainer Details on Database using Hibernate
    * </p>
    * @param - trainer
    *		object has to passed to perform Update operation
    * @return - Element
    * 		This method will Old Object
    **/
    public boolean updateTrainer(Trainer trainee);
}