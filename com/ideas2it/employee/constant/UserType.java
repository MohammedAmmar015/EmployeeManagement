/**
 * <p>
 * This is Enum class which has Employee/User Type
 *    1. Trainee
 *    2. Trainer
 * </p>
 * @author Mohammed Ammar
 * @version 1.0
 * @since 27-08-2022
 **/
package com.ideas2it.employee.constant;


public enum UserType {
    TRAINEE("Trainee"),
    TRAINER("Trainer");
	
    public String value;
	
    /**
    * <p>
    * This is Constructor used to Set value to Variable
    * </p>
    * @param userType
    **/
    UserType(String userType) {
	this.value = userType;
    }
}