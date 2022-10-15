/**
 * <p>
 * TraineeService class - This is a Service class for Trainee,
 * To validate user input through Util classes
 * It has methods to Validate the details
 * To add, get, modify and remove
 * </p>
 *
 * @author Mohammed Ammar
 * @version 1.0
 * @since 12/08/2022
 **/
package com.ideas2it.employee.service.impl;

import com.ideas2it.employee.constant.Attributes;
import com.ideas2it.employee.constant.ErrorMessage;
import com.ideas2it.employee.dao.QualificationDao;
import com.ideas2it.employee.dao.RoleDao;
import com.ideas2it.employee.dao.TraineeDao;
import com.ideas2it.employee.dto.TraineeDto;
import com.ideas2it.employee.exception.BadRequest;
import com.ideas2it.employee.exception.TraineeNotFound;
import com.ideas2it.employee.mapper.TraineeMapper;
import com.ideas2it.employee.models.Qualification;
import com.ideas2it.employee.models.Role;
import com.ideas2it.employee.models.Trainee;
import com.ideas2it.employee.models.Trainer;
import com.ideas2it.employee.service.TraineeService;
import com.ideas2it.employee.service.TrainerService;
import com.ideas2it.employee.utilities.DateUtil;
import com.ideas2it.employee.utilities.StringUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class TraineeServiceImpl implements TraineeService {
    private Logger logger = LogManager.getLogger(TraineeServiceImpl.class);
    private TrainerService trainerService;
    private TraineeDao traineeDao;
    private QualificationDao qualificationDao;
    private RoleDao roleDao;
    private TraineeMapper traineeMapper;

    @Autowired
    public TraineeServiceImpl(TrainerService trainerService, TraineeDao traineeDao,
                              QualificationDao qualificationDao, RoleDao roleDao,
                              TraineeMapper traineeMapper) {
        this.trainerService = trainerService;
        this.traineeDao = traineeDao;
        this.qualificationDao = qualificationDao;
        this.roleDao = roleDao;
        this.traineeMapper = traineeMapper;
    }

    /**
     * <p>
     * This method is to Validate and add Trainee Details
     * </p>
     * @param traineeDto - object of trainee
     * @throws BadRequest
     *		It throws exceptions, If any data is Invalid
     * @return errors
     *         It returns List of Attributes, which failed validation
     **/
    @Override
    public int addOrModifyTrainee(TraineeDto traineeDto) throws BadRequest {
        logger.info("Entered addOrModifyTrainee() method");
        List<Attributes> errors = new ArrayList<>();
        StringBuilder errorMessage = new StringBuilder();

        String validName = traineeDto.getName();
        if (!StringUtil.isValidName(validName)) {
            errors.add(Attributes.NAME);
            errorMessage.append(ErrorMessage.NAME.errorMessage);
        }

        Long validMobileNumber = traineeDto.getMobileNumber();
        if (!StringUtil.isValidMobileNumber(validMobileNumber.toString())) {
            errors.add(Attributes.MOBILE_NUMBER);
            errorMessage.append(ErrorMessage.MOBILE_NUMBER.errorMessage);
        }

        String validEmail = traineeDto.getEmail();
        if (!StringUtil.isValidEmail(validEmail)) {
            errors.add(Attributes.EMAIL);
            errorMessage.append(ErrorMessage.EMAIL.errorMessage);
        }

        LocalDate validDateOfJoining = traineeDto.getDateOfJoining();
        if (DateUtil.computeDays(validDateOfJoining, LocalDate.now()) < 1) {
            errors.add(Attributes.DATE_OF_JOINING);
            errorMessage.append(ErrorMessage.DATE_OF_JOINING.errorMessage);
        }

        LocalDate validDateOfBirth = traineeDto.getDateOfBirth();
        if (DateUtil.computePeriod(validDateOfBirth, LocalDate.now()) < 18) {
            errors.add(Attributes.DATE_OF_BIRTH);
            errorMessage.append(ErrorMessage.DATE_OF_BIRTH.errorMessage);
        }

        Trainee savedTrainee = null;
        if (errors.isEmpty()) {
            Set<Trainer> trainers = Set.copyOf(trainerService.getTrainersByIds(traineeDto.getTrainerIds()));
            Trainee trainee = traineeMapper.toTrainee(traineeDto);
            trainee.setTrainers(trainers);

            Optional<Qualification> qualification = qualificationDao.findByDescription(trainee.getQualification().getDescription());
            if (qualification.isPresent()) {
                trainee.setQualification(qualification.get());
            }

            Optional<Role> role = roleDao.findByDescription(trainee.getRole().getDescription());
            if (role.isPresent()) {
                trainee.setRole(role.get());
            }

            savedTrainee = traineeDao.save(trainee);
        } else {
            errorMessage.append("\t\t\t\nPlease Re-enter the Trainee details correctly");
            errorMessage.append(errors.size()).append(" Errors Found");
            throw new BadRequest(errors, errorMessage.toString());
        }
        return savedTrainee.getId();
    }

    /**
     * <p>
     * This method is used to get and return List of Trainees
     * </p>
     * @return - It returns List of Trainees
     **/
    @Override
    public List<TraineeDto> getTrainees() {
        logger.info("Entered getTrainees() method");
        List<TraineeDto> trainees = new ArrayList<>();
        for (Trainee trainee: traineeDao.findAll()) {
            trainees.add(traineeMapper.toTraineeDto(trainee));
        }
        return trainees;
    }

    /**
     * <p>
     * This method is used to get and return One particular Trainee
     * Based on Employee Id
     * </p>
     * @param traineeId - Employee/Trainee Id
     * @return - It returns single Trainee
     **/
   @Override
    public TraineeDto getTraineeById(final int traineeId) {
       logger.info("Entered getTraineeById() method");
       TraineeDto traineeDto = null;
        Optional<Trainee> retrivedTrainee = traineeDao.findById(traineeId);
        if (retrivedTrainee.isPresent()) {
            traineeDto = traineeMapper.toTraineeDto(retrivedTrainee.get());
        }
        return traineeDto;
    }

    /**
     * <p>
     * This method is used to remove Trainee object using Trainee Id
     * </p>
     * @param traineeId - Employee/Trainee Id
     * @throws TraineeNotFound
     *   	Exception will e thrown, When given id Not found
     * @return - It returns nothing
     **/
    @Override
    public boolean removeTraineeById(final int traineeId) {
        logger.info("Entered removeTraineeById() method");
        traineeDao.deleteById(traineeId);
        return true;
    }
}