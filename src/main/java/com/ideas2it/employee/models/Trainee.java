/**
 * <p>
 * Trainee class - This is POJO or Model class For Trainee
 * </p>
 *
 * @author Mohammed Ammar
 * @version 1.0
 * @since 12/08/2022
 **/
package com.ideas2it.employee.models;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Trainee extends Employee {

    @Column(name = "training_period")
    private Byte trainingPeriod;

    @Column(name = "course")
    private String course;

    @Column(name = "batch_number")
    private Byte batchNumber;
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "employee_relation",
            joinColumns = @JoinColumn(name = "trainee_id"),
            inverseJoinColumns = @JoinColumn(name = "trainer_id"))
    private Set<Trainer> trainers;

    public Trainee() {

    }

    /**
     * <p>
     * This is Constructor for Trainee class
     * </p>
     *
     * @param trainingPeriod - Trainee training Period(In Months)
     * @param course         - Course, Trainee undergoing
     * @param batchNumber    - Trainee Batch Number
     **/
    public Trainee(String name, String address, Long mobileNumber,
                   String email, LocalDate dateOfJoining, LocalDate dateOfBirth,
                   String bloodGroup, Qualification qualification, Role role,
                   Byte trainingPeriod, String course, Byte batchNumber,
                   Set<Trainer> trainers) {
        super(name, address, mobileNumber, email, dateOfJoining, dateOfBirth, bloodGroup, qualification, role);
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

    public Set<Trainer> getTrainers() {
        return trainers;
    }

    public void setTrainers(Set<Trainer> trainers) {
        this.trainers = trainers;
    }

}