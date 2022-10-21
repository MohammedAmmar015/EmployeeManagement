package com.ideas2it.employee.mapper;

import com.ideas2it.employee.dto.TraineeDto;
import com.ideas2it.employee.models.Trainee;
import com.ideas2it.employee.models.Trainer;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TraineeMapper {
    public static Trainee convertTraineeDtoToTrainee(TraineeDto traineeDto) {
        Trainee trainee = new Trainee();
        trainee.setId(traineeDto.getId());
        trainee.setName(traineeDto.getName());
        trainee.setAddress(traineeDto.getAddress());
        trainee.setEmail(traineeDto.getEmail());
        trainee.setMobileNumber(traineeDto.getMobileNumber());
        trainee.setDateOfBirth(traineeDto.getDateOfBirth());
        trainee.setDateOfJoining(traineeDto.getDateOfJoining());trainee.setBloodGroup(traineeDto.getBloodGroup());
        trainee.setRole(RoleMapper.convertRoleDtoToRole(traineeDto.getRoleDto()));
        trainee.setQualification(QualificationMapper.convertQualificationDtoToQualification(traineeDto.getQualificationDto()));
        trainee.setBatchNumber(traineeDto.getBatchNumber());
        trainee.setCourse(traineeDto.getCourse());
        trainee.setTrainingPeriod(traineeDto.getTrainingPeriod());
        return trainee;
    }

    public static TraineeDto convertObjectToTraineeDto(Object[] object) {
        TraineeDto traineeDto = new TraineeDto();
        traineeDto.setBatchNumber((Byte) object[0]);
        traineeDto.setCourse((String) object[1]);
        traineeDto.setTrainingPeriod((Byte) object[2]);
        traineeDto.setId((Integer) object[3]);
        traineeDto.setAddress((String) object[4]);
        traineeDto.setBloodGroup((String) object[5]);
        traineeDto.setEmail((String) object[8]);
        traineeDto.setName((String) object[10]);
        return traineeDto;
    }

    public static TraineeDto convertTraineeToTraineeDto(Trainee trainee) {
        TraineeDto traineeDto = new TraineeDto();
        traineeDto.setId(trainee.getId());
        traineeDto.setName(trainee.getName());
        traineeDto.setAddress(trainee.getAddress());
        traineeDto.setEmail(trainee.getEmail());
        traineeDto.setMobileNumber(trainee.getMobileNumber());
        traineeDto.setDateOfBirth(trainee.getDateOfBirth());
        traineeDto.setDateOfJoining(trainee.getDateOfJoining());
        traineeDto.setBloodGroup(trainee.getBloodGroup());
        traineeDto.setRoleDto(RoleMapper.convertRoleToRoleDto(trainee.getRole()));
        traineeDto.setQualificationDto(QualificationMapper.convertQualificationToQualificationDto(trainee.getQualification()));
        traineeDto.setBatchNumber(trainee.getBatchNumber());
        traineeDto.setCourse(trainee.getCourse());
        traineeDto.setTrainingPeriod(trainee.getTrainingPeriod());
        List<String> trainerNames = new ArrayList<>();
        List<Integer> trainerIds = new ArrayList<>();
        for (Trainer trainer : trainee.getTrainers()) {
            trainerIds.add(trainer.getId());
            trainerNames.add(trainer.getName());
        }
        traineeDto.setTrainerIds(trainerIds);
        traineeDto.setTrainersName(trainerNames);
        return traineeDto;
    }
}
