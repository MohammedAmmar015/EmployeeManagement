/**
 * <p>
 * Trainer class - This is POJO or Model class For Trainer
 * </p>
 * @author Mohammed Ammar
 * @version 1.0 
 * @since 12/08/2022
 *
 **/
package com.ideas2it.employee.models;
import com.ideas2it.employee.constant.Constants;
import com.ideas2it.employee.utilities.NumberUtil;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.HashSet;


public class Trainer extends Employee {
    private int trainerId;
    private int trainingExperience;
    private Employee employee;
    private Set<Trainee> trainees;

    public Trainer() {

    }

    /**
    * <p> 
    * This is Constructor for Trainer class
    * </p>
    * @param employee - Employee object which has employee details
    * @param trainingExperience - Number of trainees under this trainer
    **/
    public Trainer(Employee employee, int trainingExperience) {
	this.employee = employee;
	this.trainingExperience = trainingExperience;
    }

    public int getTrainerId() {
	return trainerId;
    }

    public void setTrainerId(int trainerId) {
	this.trainerId = trainerId;
    }

    public int getTrainingExperience() {
	return this.trainingExperience;
    }

    public void setTrainingExperience(int trainingExperience) {
	this.trainingExperience = trainingExperience;
    }

    public Employee getEmployee() {
	return employee;
    }

    public void setEmployee(Employee employee) {
	this.employee = employee;
    }

    public void setTrainees(Set<Trainee> trainees) {
	this.trainees = trainees;
    } 

    public Set<Trainee> getTrainees() {
	return trainees;
    }

    @Override
    public String toString() {
	int numberOfTrainees = trainees.size();
	return String.format(Constants.TRAINER_STRING_FORMAT,
			    employee.getId(), employee.getName(), employee.getAddress(), employee.getMobileNumber(), employee.getEmail(), 
			    employee.getDateOfJoining(), employee.getBloodGroup().toString(), trainingExperience, NumberUtil.getAge(employee.getDateOfBirth()), 
			    NumberUtil.getExperience(employee.getDateOfJoining()), employee.getQualification(), numberOfTrainees);
    } 
}