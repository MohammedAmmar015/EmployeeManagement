/**
 * <p>
 * TrainerDao class - This is DAO class which has List that store trainer objects,
 * It also has methods to do CRUD operations on List
 * Insert, Retrieve, Update, Delete
 * </p>
 *
 * @author Mohammed Ammar
 * @version 1.0
 * @since 12/08/2022
 **/
package com.ideas2it.employee.dao.impl;

import com.ideas2it.employee.dao.inter.TrainerDao;
import com.ideas2it.employee.models.Qualification;
import com.ideas2it.employee.models.Role;
import com.ideas2it.employee.models.Trainer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TrainerDaoImpl implements TrainerDao {

    private SessionFactory factory = new Configuration().configure("hibernate/properties/hibernate.cfg.xml").buildSessionFactory();
    private Session session;
    private Logger logger = LogManager.getLogger(TrainerDaoImpl.class);

    /**
     * <p>
     * This method is used to Insert or Update Trainer details into database using Hibernate
     * </p>
     *
     * @param trainer trainer object has to be passed to store
     **/
    @Override
    public void insertOrUpdateTrainer(Trainer trainer) {
	logger.info("Entered insertOrUpdateTrainer() method");
        try {
            session = factory.openSession();
            Transaction transaction = session.beginTransaction();

            Role roleResult = (Role) session.createQuery("from Role where description = :roleDescription")
                    .setParameter("roleDescription", trainer.getEmployee().getRole().getDescription())
                    .uniqueResult();
            if (roleResult != null) {
                trainer.getEmployee().setRole(roleResult);
            }

            Qualification qualificationResult = (Qualification) session.createQuery("from Qualification where description = :description")
                    .setParameter("description", trainer.getEmployee().getQualification().getDescription())
                    .uniqueResult();
            if (qualificationResult != null) {
                trainer.getEmployee().setQualification(qualificationResult);
            }
            session.saveOrUpdate(trainer);
            transaction.commit();
        } catch (Throwable exception) {
            exception.printStackTrace();
        } finally {
            session.close();
        }
    }

    /**
     * <p>
     * This is method is used to Retrieve all Trainers from the Database using Hibernate
     * </p>
     * @return - trainers
     *		 It returns all trainers from the List
     **/
    @Override
    public List<Trainer> retrieveTrainers() {
	logger.info("Entered retriveTrainers() method");
        List<Trainer> trainers = new ArrayList();
        try {
            session = factory.openSession();
            Transaction transaction = session.beginTransaction();
            trainers = session.createQuery("from Trainer").list();
            transaction.commit();
        } catch (Throwable exception) {
            exception.printStackTrace();
        } finally {
            session.close();
        }
        return trainers;
    }

    /**
     * <p>
     * This method is used to Delete Trainer Object from the Database using Hibernate
     * </p>
     * @param trainerId
     * 		Trainer id has to be passed to this method
     *  		to get deleted the Trainer Object
     **/
    @Override
    public boolean deleteTrainerById(int trainerId) {
	logger.info("Entered deleteTrainerById() method");
        boolean isDeleted = false;
        try {

            session = factory.openSession();
            Transaction transaction = session.beginTransaction();
            Trainer result = (Trainer) session.createQuery("from Trainer where employee.id = :id")
                    .setParameter("id", trainerId)
                    .uniqueResult();
            logger.info(result);
            if (result != null) {
                session.remove(result);
                isDeleted = true;
            }
            transaction.commit();
        } catch (Throwable exception) {
            exception.printStackTrace();
        } finally {
            session.close();
        }
        return isDeleted;
    }

    /**
     * <p>
     * This method is used to Retrieve the particular Trainer details from database using Hibernate
     * Based on Trainer Id
     * </p>
     * @param trainerId
     *		Trainer id has to be passed to this method to retrieve
     * @return trainer
     *		 This method will return trainer Object based on Trainer id
     **/
    @Override
    public Trainer retrieveTrainerById(int trainerId) {
	logger.info("Entered retriveTrainerById() method");
        Trainer trainer = null;
        try {
            session = factory.openSession();
            Transaction transaction = session.beginTransaction();
            Trainer result = (Trainer) session.createQuery("from Trainer where employee.id = :id")
                    .setParameter("id", trainerId)
                    .uniqueResult();
            if (result != null) {
                trainer = result;
            }
            transaction.commit();
        } catch (Throwable exception) {
            exception.printStackTrace();
        } finally {
            session.close();
        }
        return trainer;
    }

    @Override
    public List<Trainer> retrieveTrainersByIds(List<Integer> trainerIds) {
        logger.info("Entered retriveTrainersByIds() method");
        List<Trainer> trainers = null;
        try {
            session = factory.openSession();
            Transaction transaction = session.beginTransaction();
            trainers = (List<Trainer>) session.createQuery("from Trainer where employee.id in :ids")
                    .setParameter("ids", trainerIds)
                    .list();
            transaction.commit();
        } catch (Throwable exception) {
            exception.printStackTrace();
        } finally {
            session.close();
        }
        return trainers;
    }
}