/**
 *
 * StringUtil class - This is Util class
 * It has Static methods for String related Validation Like Name Validation, Email Validation 
 * @author Mohammed Ammar
 * @version 1.0 
 * @since 12/08/2022
 *
 **/
package com.ideas2it.employee.utilities;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class StringUtil {
  	
    /**
    * <p> 
    * This method is used to Check for Valid String Length
    * </p>
    * @param value value to check for valid length
    * @param minimumLength minimum valid length
    * @param maximumLength maximum valid length
    * @return - It returns Boolean value for Valid length
    * Example : value - example, minimumLength - 3, maximumLength - 16
    *		return true
    **/
    public static boolean isStringLengthValid(final String value, 
					      final int minimumLength,
					      final int maximumLength) {
	return value.length() >= minimumLength &&
			value.length() <= maximumLength;
    } 

    /**
    * <p> 
    * This method is used to Check for Valid Name
    * </p>
    * @param name to validate name
    * @return - It returns Boolean value, For Valid Name
    * Example : name - example@ - false
    *                  example1 - false
    *                  example  - true
    **/
    public static boolean isValidName(final String name) {
	boolean isValidName = true;
	if (isStringLengthValid(name, 3, 16)) {
	    for (int i = 0; i < name.length(); i++) {
	        char character = name.charAt(i);
		if (isSpecialCharacter(character) && 
			!Character.isLetter(character) && 
			    !Character.isWhitespace(character)) {
		    isValidName = false;
		    break;
	        }	
	    }    
	} else {
	    isValidName = false;
	}
	return isValidName;
    }

    /**
    * <p> 
    * This method is used to Check for any Special Character in a given character
    * </p>
    * @param character character to check for valid symbol
    * @return - It returns Boolean value, If special character Found
    * Example : character - a - false
    * 		            1 - false
    *                       @ - true
    **/
    public static boolean isSpecialCharacter (final char character) {
	return !Character.isDigit(character) ||
			!Character.isLetter(character) ||
			!Character.isWhitespace(character);
    }

    /**
    * <p> 
    * This method is used to check for Valid Email Id
    * </p>
    * @param emailId to validate email id
    * @return - It returns Boolean value, If Email id is Valid
    * Example : emailId - example@mail.com      - false
    *                     example@ideas2it.com  - true
    **/
    public static boolean isValidEmail (final String emailId) {
	Pattern emailPattern = Pattern.compile("^[a-zA-Z0-9+_.-]+@[ideas2it.com]+$");
	Matcher matcher = emailPattern.matcher(emailId);
	return matcher.matches();
    }

    /**
    * <p> 
    * This method is used to check for Valid Mobile Number
    * </p>
    * @param mobileNumber to check and validate mobile number
    * @return - It returns Boolean value, If Mobile Number is Valid
    * Example : mobileNumber - 323213     - false
    *			       exampleNumber - false
    *                          9999999999 - true
    **/
    public static boolean isValidMobileNumber(final String mobileNumber) { 
	boolean isValidMobileNumber = true;
	if (mobileNumber.length() == 10) {
	    for (int i = 0; i < mobileNumber.length(); i++) {
		char character = mobileNumber.charAt(i);
		if (isSpecialCharacter(character) && !Character.isDigit(character)) {
		    isValidMobileNumber = false;
		}
	    }
	} else {
	    isValidMobileNumber = false;
	}
	return isValidMobileNumber;
    }
}

