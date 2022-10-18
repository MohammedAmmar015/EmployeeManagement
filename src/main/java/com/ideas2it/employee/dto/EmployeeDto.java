/**
 * <p>
 * Employee class - This is POJO or Model class For Employee
 * </p>
 *
 * @author Mohammed Ammar
 * @version 1.0
 * @since 12/08/2022
 **/
package com.ideas2it.employee.dto;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class EmployeeDto {

    private int id;
    private String name;
    private String address;
    private Long mobileNumber;
    private String email;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfJoining;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;
    private String bloodGroup;
    private QualificationDto qualificationDto;
    private RoleDto roleDto;

    public EmployeeDto() {

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
    public EmployeeDto(String name, String address, Long mobileNumber,
                       String email, LocalDate dateOfJoining, LocalDate dateOfBirth,
                       String bloodGroup, QualificationDto qualification, RoleDto role) {
        this.name = name;
        this.address = address;
        this.mobileNumber = mobileNumber;
        this.email = email;
        this.dateOfJoining = dateOfJoining;
        this.dateOfBirth = dateOfBirth;
        this.bloodGroup = bloodGroup;
        this.qualificationDto = qualification;
        this.roleDto = role;
    }

    public EmployeeDto(int id, String name, String address, Long mobileNumber,
                       String email, LocalDate dateOfJoining, LocalDate dateOfBirth,
                       String bloodGroup, QualificationDto qualification, RoleDto role) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.mobileNumber = mobileNumber;
        this.email = email;
        this.dateOfJoining = dateOfJoining;
        this.dateOfBirth = dateOfBirth;
        this.bloodGroup = bloodGroup;
        this.qualificationDto = qualification;
        this.roleDto = role;
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

    public void setDateOfJoining(LocalDate dateOfJoining) {
        this.dateOfJoining = dateOfJoining;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getBloodGroup() {
        return this.bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public QualificationDto getQualificationDto() {
        return this.qualificationDto;
    }

    public void setQualificationDto(QualificationDto qualificationDto) {
        this.qualificationDto = qualificationDto;
    }

    public RoleDto getRoleDto() {
        return this.roleDto;
    }

    public void setRoleDto(RoleDto roleDto) {
        this.roleDto = roleDto;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", mobileNumber=" + mobileNumber +
                ", email='" + email + '\'' +
                ", dateOfJoining=" + dateOfJoining +
                ", dateOfBirth=" + dateOfBirth +
                ", bloodGroup='" + bloodGroup + '\'' +
                ", qualification=" + qualificationDto +
                ", role=" + roleDto +
                '}';
    }
}