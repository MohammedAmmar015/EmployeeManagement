/**
 * <p>
 * This is Enum class which has Error Messages 
 * </p>
 * @author Mohammed Ammar
 * @version 1.0
 * @since 27-08-2022
 **/
package com.ideas2it.employee.constant;


public enum ErrorMessage {
    NAME("\nInvalid Name, Name must have atleast 3 Character"),
    MOBILE_NUMBER("\nInvalid Mobile Number, It must have 10 digits"),
    EMAIL("\nInvalid Email, It must End with ideas2it.com"),
    DATE_OF_JOINING("\nInvalid Date of joining, Shouldn't have Future Date","\nInvalid Date Format, Date of Joining"),
    DATE_OF_BIRTH("\nInvalid Date of Birth, Trainee must be atleast 18 years old","\nInvalid Date Format, Date of Birth"),
    TRAINING_PERIOD("\nInvalid Training Period"),
    BATCH_NUMBER("\nInvalid Batch Number"),
    TRAINING_EXPERIENCE("\nInvalid Training Experience"),
    TRAINER_NAME("Invalid Trainer Name,Enter Valid Trainer Name:");
	
    public String errorMessage;
    public String formatErrorMessage;
	
    /**
    * <p>
    * This is Constructor used to Set values to errorMessage
    * </p>
    * @param errorMessage
    **/
    ErrorMessage(String errorMessage) {
	this.errorMessage = errorMessage;
    }
  
    /**
    * <p>
    * This is Constructor used to Set values to Variable
    * </p>
    * @param errorMessage
    * @param formatErrorMessage
    **/
    ErrorMessage(String errorMessage, String formatErrorMessage) {
	this.errorMessage = errorMessage;
	this.formatErrorMessage = formatErrorMessage;
    }
}