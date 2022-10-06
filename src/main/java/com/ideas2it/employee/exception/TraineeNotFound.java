/**
* This is Trainee Not Found Exception class
* @author Mohammed Ammar
* @version 1.0
* @since 18/08/2022
**/
package com.ideas2it.employee.exception;


public class TraineeNotFound extends RuntimeException {
    
    /**
    * This is Constructor, used to Set error Message
    * @param message
    * 	      - Exception or Error Message
    **/
    public TraineeNotFound(String message) {
	super(message);
    }
}