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

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "trainee")
public class Trainee extends Employee {

    @Column(name = "training_period")
    private Byte trainingPeriod;

    @Column(name = "course")
    private String course;

    @Column(name = "batch_number")
    private Byte batchNumber;

    @Transient
    private List<Integer> trainerIds;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "employee_relation",
            joinColumns = @JoinColumn(name = "trainee_id", referencedColumnName = "emp_id"),
            inverseJoinColumns = @JoinColumn(name = "trainer_id", referencedColumnName = "emp_id"))
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
    **/
    public Trainee(Employee employee, Byte trainingPeriod, String course, Byte batchNumber, Set<Trainer> trainers) {
	this.trainingPeriod = trainingPeriod;
	this.course = course;
	this.batchNumber = batchNumber;
	this.trainers = trainers;
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

    public List<Integer> getTrainerIds() {
	return trainerIds;
    }

    public void setTrainerIds(List<Integer> trainerIds) {
	this.trainerIds = trainerIds;
    }

    public void setTrainers(Set<Trainer> trainers) {
	this.trainers = trainers;
    }

    public Set<Trainer> getTrainers() {
	return trainers;
    }

    @Override
    public String toString() {
        return "Trainee{" +
                "trainingPeriod=" + trainingPeriod +
                ", course='" + course + '\'' +
                ", batchNumber=" + batchNumber +
                ", trainerIds=" + trainerIds +
                ", trainers=" + trainers +
                '}';
    }
}