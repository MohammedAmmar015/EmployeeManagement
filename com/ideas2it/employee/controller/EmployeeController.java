 /**
* <p>
* Employee Management
* Employee Controller class - used to interact and get input from users
* To display the list of trainers and trainees
* </p>
* @author Mohammed Ammar
* @version 1.0 
* @since 12/08/2022
**/
package com.ideas2it.employee.controller;

import com.ideas2it.employee.constant.Constants;
import com.ideas2it.employee.constant.BloodGroup;
import com.ideas2it.employee.constant.UserType;
import com.ideas2it.employee.constant.Attributes;
import com.ideas2it.employee.service.inter.TrainerService;
import com.ideas2it.employee.service.inter.TraineeService;
import com.ideas2it.employee.service.impl.TrainerServiceImpl;
import com.ideas2it.employee.service.impl.TraineeServiceImpl;
import com.ideas2it.employee.utilities.CommonUtil;
import com.ideas2it.employee.models.Trainer;
import com.ideas2it.employee.models.Trainee;
import com.ideas2it.employee.models.Role;
import com.ideas2it.employee.exception.BadRequest;
import com.ideas2it.employee.exception.TraineeNotFound;
import com.ideas2it.employee.exception.TrainerNotFound;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class EmployeeController extends Throwable {
    private static Scanner scanner = new Scanner(System.in);
    private TraineeService traineeServiceImpl = new TraineeServiceImpl();
    private TrainerService trainerServiceImpl = new TrainerServiceImpl();
    private Logger logger = LogManager.getLogger(EmployeeController.class);

    public static void main(String[] Args) {
	EmployeeController employeeController = new EmployeeController();
	employeeController.getUserOption();
    }

    /**
    * <p>
    * This method is used to Display Menu, and get User option
    * and Route to that case
    * </p>
    **/
    private void getUserOption() {
	int userOption = 0;
	do {
	    userOption = 0;
	    CommonUtil.displayMenu();
	    try {
	        userOption = scanner.nextInt();
		scanner.nextLine();
	    } catch (InputMismatchException e) {
		scanner = new Scanner(System.in);
	    } 
	    switch (userOption) {
	        case 1:
		    getEmployeeDetails(UserType.TRAINEE); 
		    break;

	        case 2:
		    logger.info("\t Trainees List ");
		    viewTrainees();
		    break;
	    
	        case 3:
		    removeTrainee();
		    break;

	        case 4:
		    updateTraineeDetails(UserType.TRAINEE);
		    break;

 	        case 5:
		    getEmployeeDetails(UserType.TRAINER);
		    break;

	        case 6:
		    logger.info("\t Trainers List ");
		    viewTrainers();
		    break;
	
	        case 7:
		    removeTrainer();
		    break;

	        case 8:
		    updateTrainerDetails(UserType.TRAINER);
		    break;
		
	        case 9:
		    System.exit(0);

	        default:
		    logger.warn("Invalid choice");
	    }
	} while (true);
    }

    /**
     * <p>
     * This method is used to get Employee Details from User
     * Based on Employee Type
     * 	    1. TRAINEE
     * 	    2. TRAINER
     * </p>
     * @param - userType
     *          Employee Type, Trainee or Trainer
     **/
    private boolean getEmployeeDetails(UserType userType) {
	int numberOfTrainers = 0;
	int userChoice = 0;
	List<Attributes> errors = new ArrayList<>();
	String name = getUserInput(userType, Attributes.NAME);
	String address = getUserInput(userType, Attributes.ADDRESS);
	String mobileNumber = getUserInput(userType, Attributes.MOBILE_NUMBER);
 	String email = getUserInput(userType, Attributes.EMAIL);
	String dateOfJoining = getUserInput(userType, Attributes.DATE_OF_JOINING);
	String dateOfBirth = getUserInput(userType, Attributes.DATE_OF_BIRTH);
	String qualification = getUserInput(userType, Attributes.QUALIFICATION);
	String bloodGroup = getBloodGroup(userType, Attributes.BLOOD_GROUP);
	String trainingPeriod = null;
	String course = null;
	String batchNumber = null;
	String trainingExperience = null;
	List<String> trainersId = null;
	if (UserType.TRAINEE == userType) {
	    trainingPeriod = getUserInput(userType, Attributes.TRAINING_PERIOD);
	    course = getUserInput(userType, Attributes.COURSE);
	    batchNumber = getUserInput(userType, Attributes.BATCH_NUMBER);
	    logger.info("Number of Trainers to Add(Atleast 1 trainer):");
	    boolean isValidType = false;
	    do {
	        try {
	    	    numberOfTrainers = scanner.nextInt();
		    isValidType = true;
	    	    scanner.nextLine();
	        } catch (InputMismatchException e) {
	    	    logger.warn("Invalid Type, Please Re-enter:");
	    	    scanner = new Scanner(System.in);
	        }
	    } while (!isValidType);
	    trainersId = getTrainersId(userType, Attributes.TRAINER_NAME, numberOfTrainers);
	} else if (UserType.TRAINER == userType) {
	    trainingExperience = getUserInput(userType, Attributes.TRAINING_EXPERIENCE);
	}
	do {
	    try {
		if (UserType.TRAINEE == userType) {
		    Role role = new Role("Trainee");
		    errors = traineeServiceImpl.addTrainee(name, address, mobileNumber, email, dateOfJoining, dateOfBirth,
			                                   qualification, bloodGroup, trainingPeriod, course, batchNumber, role, trainersId);	
		} else {
		    Role role = new Role("Trainer");
		    errors = trainerServiceImpl.addTrainer(name, address, mobileNumber, email, dateOfJoining, dateOfBirth,
		                                           qualification, bloodGroup, trainingExperience, role);
		}
	    } catch (BadRequest e) {
		logger.error(e.getMessage());
		errors = e.getErrors();
	    }
	    for(Attributes errorChoice : errors) {
	    	switch (errorChoice) {
	    	    case NAME:
	                name = getUserInput(userType, Attributes.NAME);
		        break;

	    	    case MOBILE_NUMBER:
	                mobileNumber = getUserInput(userType, Attributes.MOBILE_NUMBER);
		        break;

	    	    case EMAIL:
	    	        email = getUserInput(userType, Attributes.EMAIL);
		        break;
	
	    	    case DATE_OF_JOINING:
		        dateOfJoining = getUserInput(userType, Attributes.DATE_OF_JOINING);
		        break;

 	    	    case DATE_OF_BIRTH:
		        dateOfBirth = getUserInput(userType, Attributes.DATE_OF_BIRTH);
		        break;
		
		    case TRAINING_PERIOD:
		        trainingPeriod = getUserInput(userType, Attributes.TRAINING_PERIOD);
		        break;
		
		    case BATCH_NUMBER:
		        batchNumber = getUserInput(userType, Attributes.BATCH_NUMBER);
		        break;

		    case TRAINING_EXPERIENCE:
		        trainingExperience = getUserInput(userType, Attributes.TRAINING_EXPERIENCE);
		        break;

		    case TRAINER_NAME:
		        trainersId = getTrainersId(userType, Attributes.TRAINER_NAME, numberOfTrainers);
		        break;
	
	    	    default:
		        break;
		}
	    }
	} while (!errors.isEmpty());
	return true;
    }

    /**
    * <p>
    * This method is used to Diplay List of Trainees
    * </p>
    **/
    private void viewTrainees() {
	List<Trainee> trainees = traineeServiceImpl.getTrainees();
	if (trainees.isEmpty()) {
	    logger.warn("No Data Found to Display");
	} else {
	    System.out.printf(Constants.TRAINEE_STRING_FORMAT,
			      "ID", "NAME", "ADDRESS", "MOBILE_NUMBER", "EMAIL_ID", "DOJ", "BLOOD_GROUP",
			      "TRAINING_PERIOD", "COURSE", "BATCH_NUMBER", "AGE", "EXPERIENCE", "QUALIFICATION", "TRAINERS_NAME");
	    for (Trainee eachTrainee : trainees) {
	    	logger.info(eachTrainee);
	    } 
	}
    }

    /**
    * <p>
    * This method is used to Diplay List of Trainers
    * </p>
    **/
    private void viewTrainers() {
	List<Trainer> trainers = trainerServiceImpl.getTrainers();
	if (trainers.isEmpty()) {
	    logger.warn("No Data Found to Display");
	} else {
	    System.out.printf(Constants.TRAINER_STRING_FORMAT,
			      "ID","NAME","ADDRESS","MOBILE_NUMBER","EMAIL_ID","DOJ","BLOOD_GROUP",
			      "TRAINING_EXPERIENCE","AGE","EXPERIENCE","QUALIFICATION", "NUMBER_OF_TRAINEES");
	    for (Trainer eachTrainer : trainers) {
	    	logger.info(eachTrainer);
	    } 
	}
    }

    /**
    * <p>
    * This method is used to get Trainee Id from User 
    * To perform Remove Operation
    * </p>
    **/
    private void removeTrainee() { 
	boolean isValidType = false;
	logger.info("Enter Trainee Id to delete:");
	int traineeId = 0;
	do {
	    try {
	        traineeId = scanner.nextInt();
		isValidType = true;
	        traineeServiceImpl.removeTraineeById(traineeId);
	    } catch (TraineeNotFound e) {
	        logger.warn(e.getMessage());
		scanner = new Scanner(System.in);
	    }
	} while (!isValidType);
    }

    /**
    * <p>
    * This method is used to get Trainer Id from User 
    * To perform Remove Operation
    * </p>
    **/
    private void removeTrainer() { 
	boolean isValidType = false;
	logger.info("Enter Trainer Id to delete:");
	int trainerId = 0;
	do {
	    try {
	        trainerId = scanner.nextInt();
		isValidType = true;
	        trainerServiceImpl.removeTrainerById(trainerId);
	    } catch (TrainerNotFound e) {
	        logger.warn(e.getMessage());
		scanner = new Scanner(System.in);
	    }
	} while (!isValidType);
    }

    /**
    * <p>
    * This method is used to get Trainee Id from User 
    * To perform Update Operation
    * Get user option, which column to update
    * Based on that, Route to that case
    * and get Input for that column to update
    * </p>
    * @param - userType
    *          Employee Type - Trainee/Trainer
    **/
    private boolean updateTraineeDetails(UserType userType) {
        boolean isValidType = false;
	boolean isValid = false;
	boolean isValidUserChoice = false;
	int userChoice = 0;
	int traineeId = 0;
	logger.info("Enter " + userType + " ID to update :");
	do {
	    try {
	        traineeId = scanner.nextInt();
		isValidType = true;
	    } catch (InputMismatchException e) {
		logger.warn("Invalid Type, Please Re-enter correct value");
	        scanner = new Scanner(System.in);
	    }
	} while (!isValidType);

	try { 
	    Trainee trainee = traineeServiceImpl.getTraineeById(traineeId);
	    do {
		CommonUtil.displayTraineeColumns();
	        userChoice = scanner.nextInt();
		scanner.nextLine();
		do {
		    switch (userChoice) {
		        case 1:
		            String address = getUserInput(userType, Attributes.ADDRESS);
		            isValid = traineeServiceImpl.modifyTrainee(trainee, address, Attributes.ADDRESS);
		            break;

		        case 2:
		            String mobileNumber = getUserInput(userType, Attributes.MOBILE_NUMBER);
		            isValid = traineeServiceImpl.modifyTrainee(trainee, mobileNumber, Attributes.MOBILE_NUMBER);
		            break;
 
		        case 3:
		            String email = getUserInput(userType, Attributes.EMAIL);
		            isValid = traineeServiceImpl.modifyTrainee(trainee, email, Attributes.EMAIL);
		            break;

		    	case 4:
		            String course = getUserInput(userType, Attributes.COURSE);
		            isValid = traineeServiceImpl.modifyTrainee(trainee, course, Attributes.COURSE);
		            break;
	
			case 5:
			    int numberOfTrainers = 0;
		    	    isValidType = false;
			    logger.info("Number of Trainers to Add:");
		    	    do {
	        		try {
	    	    	    	    numberOfTrainers = scanner.nextInt();
		     	    	    isValidType = true;
	    	    	     	    scanner.nextLine();
	        		} catch (InputMismatchException e) {
	    	              	    logger.warn("Invalid Type, Please Re-enter:");
	    	    	     	    scanner = new Scanner(System.in);
	        		}
	    	    	    } while (!isValidType);
			    List<String> trainersId = getTrainersId(userType, Attributes.TRAINER_NAME, numberOfTrainers);
			    isValid = traineeServiceImpl.modifyTrainerNames(trainee, trainersId, Attributes.TRAINER_NAME);
			    break;
			
			case 6:
			    traineeServiceImpl.modifyTraineeIntoDB(trainee);

		    	case 7:
			    isValidUserChoice = true;
			    isValid = true;
			    break;

		    	default:
		            logger.warn("Invalid Choice");
		        }
		    } while (!isValid);
	        } while (!isValidUserChoice);
	    } catch (TraineeNotFound e) {
	        logger.warn(e.getMessage());
	    } catch (InputMismatchException e) {
		logger.warn("Invalid Type, Please Re-enter correct value");
	        isValidUserChoice = false;
	        scanner = new Scanner(System.in);
	    }
	return true;
    }
	
    /**
    * <p>
    * This method is used to get Trainer Id from User 
    * To perform Update Operation
    * Get user option, which column to update
    * Based on that, Route to that case
    * and get Input for that column to update
    * </p>
    * @param - userType
    *          Employee Type - Trainee/Trainer
    **/
    private boolean updateTrainerDetails(UserType userType) {
	boolean isValid = false;
	boolean isValidUserChoice = false;
	int userChoice = 0;
	logger.info("Enter " + userType + " ID to update :");
	int trainerId = scanner.nextInt();
	try {
	    Trainer trainer = trainerServiceImpl.getTrainerById(trainerId);
	    do {
	    	CommonUtil.displayTrainerColumns();
		try {
	            userChoice = scanner.nextInt();
		    scanner.nextLine();
	    	} catch (InputMismatchException e) {
		    isValidUserChoice = false;
		    scanner = new Scanner(System.in);
	    	} 
		do {
		    switch (userChoice) {
		    	case 1:
		            String address = getUserInput(userType, Attributes.ADDRESS);
		            isValid = trainerServiceImpl.modifyTrainer(trainer, address, Attributes.ADDRESS);
		            break;

		    	case 2:
		            String mobileNumber = getUserInput(userType, Attributes.MOBILE_NUMBER);
		            isValid = trainerServiceImpl.modifyTrainer(trainer, mobileNumber, Attributes.MOBILE_NUMBER);
		            break;
 
		    	case 3:
		            String email = getUserInput(userType, Attributes.EMAIL);
		            isValid = trainerServiceImpl.modifyTrainer(trainer, email, Attributes.EMAIL);
		            break;

		    	case 4:
		            String trainingExperience = getUserInput(userType, Attributes.TRAINING_EXPERIENCE);
		            isValid = trainerServiceImpl.modifyTrainer(trainer, trainingExperience, Attributes.TRAINING_EXPERIENCE);
		            break;
	
			case 5:
			    trainerServiceImpl.modifyTrainerIntoDB(trainer);
		    
		    	case 6:
			    isValidUserChoice = true;
			    isValid = true; 
			    break;

		    	default:
		            logger.warn("Invalid Choice");
		    }
		} while (!isValid);
	    } while (!isValidUserChoice);
	} catch (TrainerNotFound e) {
	    logger.warn(e.getMessage());
	}
	return true;
    }
    
    /**
    * <p>
    * This method is used to get input from user as String
    * </p>
    * @param - userType
    *           Employee Type - Trainee/Trainer
    * @param - inputType
    *           Column/Attributes Name
    * @return - value
    *		 value got from User
    **/
    private String getUserInput(UserType userType, Attributes inputType) {
	logger.info("\nEnter " + userType.value + " " + inputType.value + " :");
	String value = scanner.nextLine();
	return value;
    }

    /**
    * This method is used to get List of trainer name from user
    * @param - userType
    *           Employee Type - Trainee/Trainer
    * @param - inputType
    *           Column/Attributes Name
    * @return - trainersId
    *		 List of trainers Name
    **/
    private List<String> getTrainersId(UserType userType, Attributes inputType, int numberOfTrainers) {
	List<String> trainersId = new ArrayList<>();
	for (int i = 1; i <= numberOfTrainers; i++) {
	    logger.info("Enter Trainer Id - " + i + ":");
	    trainersId.add(scanner.nextLine());
	}
	return trainersId;
    }

    /**
    * <p>
    * This method is used to Display Blood Group types and get Choice from User
    * </p>
    * @param - userType
    *           Employee Type - Trainee/Trainer
    * @param - inputType
    *           Column/Attributes Name - BLOOD_GROUP
    * @return - bloodGroup
    *		 Blood Group type got from User
    **/
    private String getBloodGroup(UserType userType, Attributes inputType) {
	String bloodGroup = null;
	int bloodGroupChoice = 0;	
	do {
	    logger.info("1.A+\n2.B+\n3.O+\n4.AB+\n5.A-\n6.B-\n7.O-\n8.AB-");
	    logger.info("Enter " + userType.value + " " + inputType.value + ":");
	    try {
	        bloodGroupChoice = scanner.nextInt();
		scanner.nextLine();
	    } catch (InputMismatchException e) {
		bloodGroupChoice = 0;
		scanner = new Scanner(System.in);
	    } 
	    switch (bloodGroupChoice) {
	        case 1:
		    bloodGroup = BloodGroup.A_POSITIVE.bloodGroup;
		    break;

	        case 2:
		    bloodGroup = BloodGroup.B_POSITIVE.bloodGroup;
		    break;

	        case 3:
		    bloodGroup = BloodGroup.O_POSITIVE.bloodGroup;
		    break;

	        case 4:
		    bloodGroup = BloodGroup.AB_POSITIVE.bloodGroup;
		    break;

	        case 5:
		    bloodGroup = BloodGroup.A_NEGATIVE.bloodGroup;
		    break;

	        case 6:
		    bloodGroup = BloodGroup.B_NEGATIVE.bloodGroup;
		    break;

	        case 7:
		    bloodGroup = BloodGroup.O_NEGATIVE.bloodGroup;
		    break;

	        case 8:
		    bloodGroup = BloodGroup.AB_NEGATIVE.bloodGroup;
		    break;

	        default:
		    logger.warn("Invalid Blood Group Choice");
	    }	
	} while (bloodGroupChoice <= 0 || bloodGroupChoice >= 9);
        return bloodGroup;
    }
}