package com.ideas2it.employee.helper;

import com.ideas2it.employee.dao.TrainerDao;
import com.ideas2it.employee.models.Trainer;
import com.ideas2it.employee.service.impl.TrainerServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TrainerHelper {

    private Logger logger = LogManager.getLogger(TrainerServiceImpl.class);
    private TrainerDao trainerDao;

    @Autowired
    public TrainerHelper(TrainerDao trainerDao) {
        this.trainerDao = trainerDao;
    }

    public List<Trainer> getTrainersByIds(final List<Integer> trainerIds) {
        logger.info("Entered getTrainerById() method");
        List<Trainer> trainers = trainerDao.findAllById(trainerIds);
        return trainers;
    }
}
