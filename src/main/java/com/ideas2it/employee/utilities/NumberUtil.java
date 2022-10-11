/**
 *
 * NumberUtil class - This is Util class
 * It has Static methods for Number related calculation. like, Age and Experience
 * @author Mohammed Ammar
 * @version 1.0 
 * @since 12/08/2022
 *
 **/
package com.ideas2it.employee.utilities;


import java.time.LocalDate;
import java.time.Period;

public class NumberUtil {

    /**
    * <p>
    * This method is used to Calculate Age
    * Based on Date of birth and Current date
    * </p>
    * @return - age
    * 		This method will age
    **/
    public static int getAge(LocalDate dateOfBirth) {
	Period agePeriod = Period.between(dateOfBirth, LocalDate.now());
    return agePeriod.getYears();
    }

    /**
    * <p>
    * This method is used to Calculate Experience
    * Based on Date of joining and Current date
    * </p>
    * @return - experience
    * 		This method will experience
    **/
    public static int getExperience(LocalDate dateOfJoining) {
	Period experiencePeriod = Period.between(dateOfJoining, LocalDate.now());
    return experiencePeriod.getYears();
    }
}