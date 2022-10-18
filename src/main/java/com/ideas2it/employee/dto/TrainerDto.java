/**
 * <p>
 * Trainer class - This is POJO or Model class For Trainer
 * </p>
 * @author Mohammed Ammar
 * @version 1.0 
 * @since 12/08/2022
 *
 **/
package com.ideas2it.employee.dto;

import java.time.LocalDate;
import java.util.Set;

public class TrainerDto extends EmployeeDto {
    private int trainingExperience;
    private int numberOfTrainees;



    public TrainerDto() {

    }

    /**
    * <p> 
    * This is Constructor for Trainer class
    * </p>
    * @param trainingExperience - Number of trainees under this trainer
    **/

    public TrainerDto(int id,
                      String name,
                      String address,
                      Long mobileNumber,
                      String email,
                      LocalDate dateOfJoining,
                      LocalDate dateOfBirth,
                      String bloodGroup,
                      QualificationDto qualification,
                      RoleDto role,
                      int trainingExperience,
                      int numberOfTrainees) {
        super( id, name, address, mobileNumber, email,
                dateOfJoining, dateOfBirth, bloodGroup,
                qualification, role);
	//this.employee = employee;
	this.trainingExperience = trainingExperience;
    this.numberOfTrainees = numberOfTrainees;
    }

    public int getTrainingExperience() {
	return this.trainingExperience;
    }

    public void setTrainingExperience(int trainingExperience) {
	this.trainingExperience = trainingExperience;
    }


    public int getNumberOfTrainees() {
        return numberOfTrainees;
    }

    public void setNumberOfTrainees(int numberOfTrainees) {
        this.numberOfTrainees = numberOfTrainees;
    }

    @Override
    public String toString() {
        return "Trainer{" +
                "trainingExperience=" + trainingExperience +
                ", trainee Names=" + numberOfTrainees +
                "qualification =" + getQualificationDto() +
        '}';
    }
}