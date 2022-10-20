/**
 * <p>
 * Trainee class - This is POJO or Model class For Trainee
 * </p>
 *
 * @author Mohammed Ammar
 * @version 1.0
 * @since 12/08/2022
 **/
package com.ideas2it.employee.dto;

import java.util.List;
import java.util.Set;

public class TraineeDto extends EmployeeDto {
    private Byte trainingPeriod;
    private String course;
    private Byte batchNumber;
    private List<Integer> trainerIds;

    private List<String> trainersName;

    public TraineeDto() {

    }

    /**
     * <p>
     * This is Constructor for Trainee class
     * </p>
     *
     * @param employee       - Employee object which has Employee Details
     * @param trainingPeriod - Trainee training Period(In Months)
     * @param course         - Course, Trainee undergoing
     * @param batchNumber    - Trainee Batch Number
     * @param trainersName
     **/
    public TraineeDto(EmployeeDto employee, Byte trainingPeriod, String course, Byte batchNumber, Set<TrainerDto> trainers, List<String> trainersName) {
        this.trainingPeriod = trainingPeriod;
        this.course = course;
        this.batchNumber = batchNumber;
        this.trainersName = trainersName;
    }

    public List<String> getTrainersName() {
        return trainersName;
    }

    public void setTrainersName(List<String> trainersName) {
        this.trainersName = trainersName;
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


    @Override
    public String toString() {
        return "Trainee{" +
                "trainingPeriod=" + trainingPeriod +
                ", course='" + course + '\'' +
                ", batchNumber=" + batchNumber +
                ", trainerIds=" + trainerIds +
                ", trainers=" +
                "qualification" + getQualificationDto() +
                '}';
    }
}