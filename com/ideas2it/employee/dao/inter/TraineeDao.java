package com.ideas2it.employee.dao.inter;
import java.util.List;
import com.ideas2it.employee.models.Trainee;
import com.ideas2it.employee.constant.Attributes;

/**
 * <p>
 * This is Interface for TraineeDao Class, which contains methods,
 * To do CRUD operations
 * Insert, Retrive, Update, Delete
 * </p>
 * @author Mohammed Ammar
 * @version 1.0 
 * @since 12/08/2022
 *
 **/
public interface TraineeDao {

    /**
    * <p>
    * This method is used to Insert Trainee details into Database
    * Using Hibernate
    * </p>
    * @param - trainee
    *		trainee object has to be passed to store
    * @return - isTraineeAdded
    *		 it returns boolean value
    **/
    public boolean insertTrainee(Trainee trainee);


    /**
    * <p>
    * This is method is used to Retrive all Trainees from the Database
    * Using Hibernate 
    * </p>
    * @return - trainees
    *		 It returns all trainees from the List
    **/
    public List<Trainee> retriveTrainees();


    /**
    * <p>
    * This method is used to Delete Trainee Details from the Database
    * Using Hibernate
    * </p>
    * @param - traineeId
    * 		Trainee Id has to be passed to this method
    *  		to get deleted the Trainee Object
    **/
    public boolean deleteTraineeById(int traineeId);


    /**
    * <p>
    * This method is used to Retrive the particular Trainee Details from Database
    * Based on Trainee Id, Using Hibernate
    * </p>
    * @param - traineeId
    *		Trainee Id has to be passed to this method to retrive
    * @return - trainee
    *		 This method will return trainee Object based on Trainee Id
    **/
    public Trainee retriveTraineeById(int traineeId);



    /**
    * <p>
    * This method is used to Update Trainee Details on Database using Hibernate
    * </p>
    * @param - trainee
    *		object has to passed to perform Update operation
    * @return - Element
    * 		This method will Old Object
    **/
    public boolean updateTrainee(Trainee trainee); 
}