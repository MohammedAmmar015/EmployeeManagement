/**
 * <p>
 * Trainer class - This is POJO or Model class For Trainer
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
public class Trainer extends Employee {
    @Column(name = "training_experience")
    private int trainingExperience;

    @ManyToMany(mappedBy = "trainers")
    private Set<Trainee> trainees;


    public Trainer() {

    }

    /**
     * <p>
     * This is Constructor for Trainer class
     * </p>
     * @param trainingExperience - Number of trainees under this trainer
     **/

    public Trainer(int id,
                   String name,
                   String address,
                   Long mobileNumber,
                   String email,
                   LocalDate dateOfJoining,
                   LocalDate dateOfBirth,
                   String bloodGroup,
                   Qualification qualification,
                   Role role,
                   int trainingExperience) {
        super(id, name, address, mobileNumber, email,
                dateOfJoining, dateOfBirth, bloodGroup,
                qualification, role);
        this.trainingExperience = trainingExperience;
    }

    public int getTrainingExperience() {
        return this.trainingExperience;
    }

    public void setTrainingExperience(int trainingExperience) {
        this.trainingExperience = trainingExperience;
    }

    public Set<Trainee> getTrainees() {
        return trainees;
    }

    public void setTrainees(Set<Trainee> trainees) {
        this.trainees = trainees;
    }

    @Override
    public String toString() {
        return "Trainer{" +
                "trainingExperience=" + trainingExperience +
                ", trainees=" + trainees +
                "qualification" + getQualification() +
                "role" + getRole() +
                '}';
    }
}