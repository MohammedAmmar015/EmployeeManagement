/**
 * <p>
 * This is Enum class which has all the Blood Group Types 
 * </p>
 * @author Mohammed Ammar
 * @version 1.0
 * @since 27-08-2022
 **/
package com.ideas2it.employee.constant;


public enum BloodGroup {

    A_POSITIVE("A Positive"),
    B_POSITIVE("B Positive"),
    O_POSITIVE("O Positive"),
    AB_POSITIVE("AB Positive"),
    A_NEGATIVE("A Negative"),
    B_NEGATIVE("B Negative"),
    O_NEGATIVE("O Negative"),
    AB_NEGATIVE("AB Negative");

    public String bloodGroup;

    /**
    * <p>
    * This is Constructor used to Set values to Variable
    * </p>
    * @param bloodgroup
    **/
    BloodGroup(String bloodGroup) {
	this.bloodGroup = bloodGroup;
    }
}