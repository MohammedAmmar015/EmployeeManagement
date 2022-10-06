/**
* This is BadRequest Exception class
* @author Mohammed Ammar
* @version 1.0
* @since 18/08/2022
**/

package com.ideas2it.employee.exception;

import com.ideas2it.employee.constant.Attributes;

import java.util.List;

public class BadRequest extends RuntimeException {
    private List<Attributes> errors;
    private Integer error;

    /**
    * This Constructor is used to set list of Errors
    * @param errors
    *		add List of errors
    * @param message
		Error Messages for Invalid Details
    **/
    public BadRequest(List<Attributes> errors, String message) {
	super(message);
	this.errors = errors;
    }  

    /**
    * This Constructor is used to set Error
    * @param error
    *		to add error
    * @param message
		Error Message for Invalid Details
    **/
    public BadRequest(Integer error, String message) {
	super(message);
	this.error = error;
    }  

    /**
    * <p>
    * This method is used to return Trainee List size
    * </p>
    * @return - It returns Trainee List Size
    **/ 
    public List<Attributes> getErrors() {
	return errors;
    }
}