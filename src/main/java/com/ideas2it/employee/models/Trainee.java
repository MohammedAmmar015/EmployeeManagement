/**
 * <p>
 * Trainee class - This is POJO or Model class For Trainee
 * </p>
 * @author Mohammed Ammar
 * @version 1.0 
 * @since 12/08/2022
 *
 **/
package com.ideas2it.employee.models;

import com.ideas2it.employee.constant.Constants;
import com.ideas2it.employee.utilities.NumberUtil;

import java.util.List;
import java.util.Set;

public class Trainee extends Employee {

    private int traineeId;
    private Byte trainingPeriod;
    private String course;
    private Byte batchNumber;
    private Employee employee;
    private List<Integer> trainersId;
    private Set<Trainer> trainers;

    public Trainee() {

    }

    /**
    * <p> 
    * This is Constructor for Trainee class
    * </p>
    * @param employee - Employee object which has Employee Details
    * @param trainingPeriod - Trainee training Period(In Months)
    * @param course - Course, Trainee undergoing
    * @param batchNumber - Trainee Batch Number
    * @param trainersId - List of trainers id
    **/
    public Trainee(Employee employee, Byte trainingPeriod, String course, Byte batchNumber, Set<Trainer> trainers) {
	this.employee = employee;
	this.trainingPeriod = trainingPeriod;
	this.course = course;
	this.batchNumber = batchNumber;
	this.trainers = trainers;
    }

    public int getTraineeId() {
	return traineeId;
    }

    public void setTraineeId(int traineeId) {
	this.traineeId = traineeId;
    }

    public Byte getTrainingPeriod() {
	return this.trainingPeriod;
    }

    public void setTrainingPeriod(Byte trainingPeriod) {
	this.trainingPeriod = trainingPeriod;
    }

    public String getCourse() {
	return this.course;
    }

    public void setCourse(String course) {
	this.course = course;
    }

    public Byte getBatchNumber() {
	return this.batchNumber;
    }

    public void setBatchNumber(Byte batchNumber) {
	this.batchNumber = batchNumber;
    }    

    public Employee getEmployee() {
	return employee;
    }

    public void setEmployee(Employee employee) {
	this.employee = employee;
    }

    public List<Integer> getTrainersId() {
	return trainersId;
    }

    public void setTrainersId(List<Integer> trainersId) {
	this.trainersId = trainersId;
    }

    public void setTrainers(Set<Trainer> trainers) {
	this.trainers = trainers;
    }

    public Set<Trainer> getTrainers() {
	return trainers;
    }

    @Override
    public String toString() {
	StringBuilder trainerNames = new StringBuilder();
	for (Trainer trainer : trainers) {
	    trainerNames.append(trainer.getEmployee().getName());
	    trainerNames.append(",");
	}
	return String.format(Constants.TRAINEE_STRING_FORMAT,
			    employee.getId(), employee.getName(), employee.getAddress(), employee.getMobileNumber(), employee.getEmail(), 
			    employee.getDateOfJoining(), employee.getBloodGroup().toString(), trainingPeriod, course, 
			    batchNumber, NumberUtil.getAge(employee.getDateOfBirth()), NumberUtil.getExperience(employee.getDateOfJoining()), 
			    employee.getQualification(), trainerNames);
    } 
}