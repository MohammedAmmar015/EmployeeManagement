/**
 * <p>
 * DateUtil class - This is Util class
 * It has Static methods for Date related Validation and Computation methods 
 * Validations like Date of Join shouldn't have future date
 * Computations like Period between two given dates
 * </p>
 * @author Mohammed Ammar
 * @version 1.0 
 * @since 12/08/2022
 *
 */
package com.ideas2it.employee.utilities;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.Period;


public class DateUtil {
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    /**
    * <p> 
    * This method is used Check for Valid Date Format
    * </p>
    * @param date date to Check for valid format
    * @return isValidDateFormat 
    * 		It returns boolean value, for Valid Date Format
    * Example - Invalid Date - yyyy/MM/dd - false
    *             Valid Date - dd/MM/yyyy - true
    **/
    public static boolean isValidDateFormat(final String date) {
	boolean isValidDateFormat = true;
	try {
	    LocalDate ParsedDate = LocalDate.parse(date,formatter);
	} catch (DateTimeParseException e) {
	    isValidDateFormat = false;
	}
	return isValidDateFormat;
    }

    /**
    * <p> 
    * This method is used to Compute years between to Two Date
    * </p>
    * @param startDate start date to find period
    * @param endDate end date
    * @return computedYears
    * Example - from 01/01/2020 to 01/01/2021
    * 		return 1
    **/
    public static int computePeriod(final LocalDate startDate, final LocalDate endDate) {
	Period period = Period.between(startDate, endDate);
	return period.getYears();
    }

    /**
    * <p> 
    * This method is used to Compute Days between to Two Date
    * </p>
    * @param startDate start date to find period
    * @param endDate end date
    * @return computedDays
    * Example - from 01/01/2022 to 10/01/2022
    * 		return 10
    **/
    public static int computeDays(final LocalDate startDate, final LocalDate endDate) {
	Period period = Period.between(startDate, endDate);
	int computedDays;
	if (period.getYears() > 0) {
	    computedDays = period.getYears();
	} else {
	    computedDays = period.getDays();
	}
	return computedDays;
    }
}