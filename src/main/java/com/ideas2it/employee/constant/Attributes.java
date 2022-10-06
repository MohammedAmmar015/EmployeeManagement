/**
 * <p>
 * This is Enum class which has all the Attributes of
 * Employee, Trainee and Trainer Class
 * </p>
 * @author Mohammed Ammar
 * @version 1.0
 * @since 27-08-2022
 **/
package com.ideas2it.employee.constant;


public enum Attributes {
    NAME("Name"),
    ADDRESS("Address"),
    MOBILE_NUMBER("Mobile Number"),
    EMAIL("Email"),
    DATE_OF_JOINING("Date of Joining"),
    DATE_OF_BIRTH("Date of Birth"),
    QUALIFICATION("Qualification"),
    BLOOD_GROUP("Blood Group"),
    TRAINING_PERIOD("Training Period"),
    COURSE("Course"),
    BATCH_NUMBER("Batch Number"),
    TRAINING_EXPERIENCE("Training Experience"),
    TRAINER_NAME("Trainer Name");

    public String value;

    /**
     * <p>
     * This is Constructor used to Set values to Variable
     * </p>
     * @param inputType
     *        Trainee or Trainer
     **/
    Attributes(String inputType) {

        this.value = inputType;
    }
}