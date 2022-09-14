/**
 *
 * CommonUtil class - This is Util class
 * It has methods to display Menu to the user
 * @author Mohammed Ammar
 * @version 1.0 
 * @since 12/08/2022
 *
 **/
package com.ideas2it.employee.utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class CommonUtil {

    private static Logger logger = LogManager.getLogger(CommonUtil.class);

    /**
    * <p> 
    * This method is used to display Menu
    * </p>
    * @return - It returns nothing
    **/
    public static void displayMenu() {
    	logger.info("\n\t\t Employee Management \n");
	System.out.print("Trainee\n"
			+ "Press 1 to Add Trainee\n"
			+ "Press 2 to List Trainee\n"
			+ "Press 3 to Remove Trainee\n"
			+ "Press 4 to Update Trainee\n\n"
			+ "Trainer\n"
		        + "Press 5 to Add Trainer\n"
			+ "Press 6 to List Trainer\n"
			+ "Press 7 to Remove Trainer\n"
			+ "Press 8 to Update Trainer\n"
			+ "Press 9 to Exit\n");
	System.out.print("Enter Choice:");
    }

    /**
    * <p> 
    * This method is used to display Common Employee Attributes
    * </p>
    * @return - It returns nothing
    **/
    private static void displayColumnNames() {
	logger.info("\t\tSelect Column to Update:\n"
			  + "1.Address\n2.Mobile Number\n3.Email");
    }

    /**
    * <p> 
    * This method is used to display Unique Trainee Attributes
    * </p>
    * @return - It returns nothing
    **/
    public static void displayTraineeColumns() {
	displayColumnNames();
	logger.info("4.Course\n5.Trainer Names\n6.Update into DB\nPress 7 to go back to menu\nEnter choice:");
    }

    /**
    * <p> 
    * This method is used to display Unique Trainer Attributes
    * </p>
    * @return - It returns nothing
    **/
    public static void displayTrainerColumns() {
	displayColumnNames();
	logger.info("4.Training Experience\n5.Update into DB\nPress 6 to go back to menu\nEnter choice:");
    }
}