/**
 * <p>
 * Employee class - This is POJO or Model class For Employee
 * </p>
 * @author Mohammed Ammar
 * @version 1.0
 * @since 12/08/2022
 *
 **/
package com.ideas2it.employee.models;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;


public class Employee {
    private int id;
    private String name;
    private String address;
    private Long mobileNumber;
    private String email;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfJoining = LocalDate.now();
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth = LocalDate.now();
    private String bloodGroup;
    private Qualification qualification;
    private Role role;
    private Trainee trainee;
    private Trainer trainer;

    public Employee() {

    }

    /**
     * <p>
     * This is Constructor for Employee class
     * </p>
     * @param name - Employee Name
     * @param address - Employee Address
     * @param mobileNumber - Employee Mobile Number
     * @param email - Employee Email
     * @param dateOfJoining - Date, Employee joined
     * @param dateOfBirth - Employee Date of Birth
     * @param bloodGroup - Employee Blood Group
     * @param qualification - Employee qualification object
     * @param role - Employee role Trainee or Trainer
     **/
    public Employee(String name, String address, Long mobileNumber,
                    String email, LocalDate dateOfJoining, LocalDate dateOfBirth,
                    String bloodGroup, Qualification qualification, Role role) {
        this.name = name;
        this.address = address;
        this.mobileNumber = mobileNumber;
        this.email = email;
        this.dateOfJoining = dateOfJoining;
        this.dateOfBirth = dateOfBirth;
        this.bloodGroup = bloodGroup;
        this.qualification = qualification;
        this.role = role;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getMobileNumber() {
        return this.mobileNumber;
    }

    public void setMobileNumber(Long mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDateOfJoining() {
        return this.dateOfJoining;
    }

    /*public void setDateOfJoining(String dateOfJoining) {
        this.dateOfJoining = LocalDate.parse(dateOfJoining);
    }

     */

    public void setDateOfJoining(LocalDate dateOfJoining) {
        this.dateOfJoining = dateOfJoining;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    /*
    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = LocalDate.parse(dateOfBirth);
    }
     */

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getBloodGroup() {
        return this.bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public Qualification getQualification() {
        return this.qualification;
    }

    public void setQualification(Qualification qualification) {
        this.qualification = qualification;
    }

    public Role getRole() {
        return this.role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Trainee getTrainee() {
        return this.trainee;
    }

    public void setTrainee(Trainee trainee) {
        this.trainee = trainee;
    }

    public Trainer getTrainer() {
        return this.trainer;
    }

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }
}