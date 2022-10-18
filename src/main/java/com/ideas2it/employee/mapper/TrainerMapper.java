package com.ideas2it.employee.mapper;

import com.ideas2it.employee.dto.TrainerDto;
import com.ideas2it.employee.models.Trainer;

public class TrainerMapper {
    public static Trainer convertTrainerDtoToTrainer(TrainerDto trainerDto) {
        Trainer trainer = new Trainer();
        trainer.setId(trainerDto.getId());
        trainer.setName(trainerDto.getName());
        trainer.setAddress(trainerDto.getAddress());
        trainer.setEmail(trainerDto.getEmail());
        trainer.setMobileNumber(trainerDto.getMobileNumber());
        trainer.setDateOfBirth(trainerDto.getDateOfBirth());
        trainer.setDateOfJoining(trainerDto.getDateOfJoining());trainer.setBloodGroup(trainerDto.getBloodGroup());
        trainer.setRole(RoleMapper.convertRoleDtoToRole(trainerDto.getRoleDto()));
        trainer.setQualification(QualificationMapper.convertQualificationDtoToQualification(trainerDto.getQualificationDto()));
        return trainer;
    }

    public static TrainerDto convertTrainerToTrainerDto(Trainer trainer) {
        TrainerDto trainerDto = new TrainerDto();
        trainerDto.setId(trainer.getId());
        trainerDto.setName(trainer.getName());
        trainerDto.setAddress(trainer.getAddress());
        trainerDto.setEmail(trainer.getEmail());
        trainerDto.setMobileNumber(trainer.getMobileNumber());
        trainerDto.setDateOfBirth(trainer.getDateOfBirth());
        trainerDto.setDateOfJoining(trainer.getDateOfJoining());
        trainerDto.setBloodGroup(trainer.getBloodGroup());
        trainerDto.setRoleDto(RoleMapper.convertRoleToRoleDto(trainer.getRole()));
        trainerDto.setQualificationDto(QualificationMapper.convertQualificationToQualificationDto(trainer.getQualification()));
        trainerDto.setNumberOfTrainees(trainer.getTrainees().size());
        return trainerDto;
    }
}
