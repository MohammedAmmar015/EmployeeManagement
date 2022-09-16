/**
* This is Trainer Not Found Exception class
* @author Mohammed Ammar
* @version 1.0
* @since 18/08/2022
**/
package com.ideas2it.employee.exception;


public class TrainerNotFound extends RuntimeException {

    /**
    * This is Constructor, used to Set error Message
    * @param message
    * 	      - Exception or Error Message
    **/
    public TrainerNotFound(String message) {
	super(message);
    }
}