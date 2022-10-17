/**
 * TrainerService class - This is a Service class for Trainer,
 * To validate user input through Util classes
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
import com.ideas2it.employee.dao.TrainerDao;
import com.ideas2it.employee.dto.TrainerDto;
import com.ideas2it.employee.exception.BadRequest;
import com.ideas2it.employee.exception.TrainerNotFound;
import com.ideas2it.employee.mapper.TrainerMapper;
import com.ideas2it.employee.models.Qualification;
import com.ideas2it.employee.models.Role;
import com.ideas2it.employee.models.Trainer;
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

@Service
public class TrainerServiceImpl implements TrainerService {
    private Logger logger = LogManager.getLogger(TrainerServiceImpl.class);
    private TrainerDao trainerDao;
    private RoleDao roleDao;
    private QualificationDao qualificationDao;

    @Autowired
    public TrainerServiceImpl(TrainerDao trainerDao, RoleDao roleDao,
                              QualificationDao qualificationDao) {
        this.trainerDao = trainerDao;
        this.roleDao = roleDao;
        this.qualificationDao = qualificationDao;
    }

    /**
     * <p>
     * This method is to Validate and insert the Trainer details
     * </p>
     *
     * @param trainerDto - trainer object
     * @return errors
     * It returns List of Attributes, which failed validation
     * @throws BadRequest Exception will be thrown, If any details get Invalid
     **/
    @Override
    public int addOrModifyTrainer(TrainerDto trainerDto) throws BadRequest {
        logger.info("Entered addOrModifyTrainer() method");
        List<Attributes> errors = new ArrayList<>();
        StringBuilder errorMessage = new StringBuilder();

        String validName = trainerDto.getName();
        if (!StringUtil.isValidName(validName)) {
            errors.add(Attributes.NAME);
            errorMessage.append(ErrorMessage.NAME.errorMessage);
        }

        Long validMobileNumber = trainerDto.getMobileNumber();
        if (!StringUtil.isValidMobileNumber(validMobileNumber.toString())) {
            errors.add(Attributes.MOBILE_NUMBER);
            errorMessage.append(ErrorMessage.MOBILE_NUMBER.errorMessage);
        }

        String validEmail = trainerDto.getEmail();
        if (!StringUtil.isValidEmail(validEmail)) {
            errors.add(Attributes.EMAIL);
            errorMessage.append(ErrorMessage.EMAIL.errorMessage);
        }

        LocalDate validDateOfJoining = trainerDto.getDateOfJoining();
        if (DateUtil.computeDays(validDateOfJoining, LocalDate.now()) < 1) {
            errors.add(Attributes.DATE_OF_JOINING);
            errorMessage.append(ErrorMessage.DATE_OF_JOINING.errorMessage);
        }

        LocalDate validDateOfBirth = trainerDto.getDateOfBirth();
        if (DateUtil.computePeriod(validDateOfBirth, LocalDate.now()) < 18) {
            errors.add(Attributes.DATE_OF_BIRTH);
            errorMessage.append(ErrorMessage.DATE_OF_BIRTH.errorMessage);
        }
        Trainer savedTrainer = null;
        if (errors.isEmpty()) {
            Trainer trainer = TrainerMapper.convertTrainerDtoToTrainer(trainerDto);
            Optional<Qualification> qualification = qualificationDao.findByDescription(trainer.getQualification().getDescription());
            if (qualification.isPresent()) {
                trainer.setQualification(qualification.get());
            }

            Optional<Role> role = roleDao.findByDescription(trainer.getRole().getDescription());
            if (role.isPresent()) {
                trainer.setRole(role.get());
            }
            savedTrainer = trainerDao.save(trainer);
        } else {
            errorMessage.append(errors.size()).append(" Errors Found");
            errorMessage.append("\t\t\tPlease Re-enter the Trainer details correctly");
            throw new BadRequest(errors, errorMessage.toString());
        }
        return savedTrainer.getId();
    }

    /**
     * <p>
     * This method is used to get and return List of Trainers
     * </p>
     *
     * @return - It returns List of Trainers
     **/
    @Override
    public List<TrainerDto> getTrainers() {
        logger.info("Entered getTrainers() method");
        List<TrainerDto> trainers = new ArrayList<>();
        for (Trainer trainer : trainerDao.findAll()) {
            trainers.add(TrainerMapper.convertTrainerToTrainerDto(trainer));
        }
        return trainers;
    }

    /**
     * <p>
     * This method is used to get and return One particular Trainer
     * Based on Employee Id
     * </p>
     *
     * @param trainerId - Employee/Trainer Id
     * @return - It returns single Trainer
     **/
    @Override
    public TrainerDto getTrainerById(final int trainerId) {
        logger.info("Entered getTrainerById() method");
        TrainerDto trainerDto = null;
        Optional<Trainer> trainer = trainerDao.findById(trainerId);
        if (trainer.isPresent()) {
            trainerDto = TrainerMapper.convertTrainerToTrainerDto(trainer.get());
        }
        return trainerDto;
    }

    @Override
    public List<Trainer> getTrainersByIds(final List<Integer> trainerIds) {
        logger.info("Entered getTrainerById() method");
        List<Trainer> trainers = trainerDao.findAllById(trainerIds);
        return trainers;
    }

    /**
     * <p>
     * This method is used to remove Trainer object using Trainer Id
     * </p>
     *
     * @param trainerId - Employee/Trainer Id
     * @return - It returns nothing
     * @throws TrainerNotFound Exception will be thrown, If Trainer Not found
     **/
    @Override
    public boolean removeTrainerById(final int trainerId) {
        logger.info("Entered removeTrainerById() method");
        trainerDao.deleteById(trainerId);
        return true;
    }
}