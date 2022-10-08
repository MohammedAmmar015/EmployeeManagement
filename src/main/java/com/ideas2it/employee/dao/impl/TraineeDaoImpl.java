/**
 * <p>
 * TrainerDao class - This DAO class has methods to do CRUD Operations on Database
 * Insert, Retrieve, Update, Delete
 * Using Hibernate
 * </p>
 *
 * @author Mohammed Ammar
 * @version 1.0
 * @since 12/08/2022
 **/
package com.ideas2it.employee.dao.impl;

import com.ideas2it.employee.dao.inter.TraineeDao;
import com.ideas2it.employee.models.Qualification;
import com.ideas2it.employee.models.Role;
import com.ideas2it.employee.models.Trainee;
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
public class TraineeDaoImpl implements TraineeDao {

    private SessionFactory factory = new Configuration().configure("hibernate/properties/hibernate.cfg.xml").buildSessionFactory();
    private Session session;
    private Logger logger = LogManager.getLogger(TraineeDaoImpl.class);

    /**
     * <p>
     * This method is used to Insert or Update trainee details into database using Hibernate
     * </p>
     *
     * @param trainee trainee object has to be passed to store
     **/
    @Override
    public void insertOrUpdateTrainee(Trainee trainee) {
        logger.info("Entered insertOrUpdateTrainee() method");
        try {
            session = factory.openSession();
            Transaction transaction = session.beginTransaction();

            Role roleResult = (Role) session.createQuery("from Role where description = :roleDescription")
                    .setParameter("roleDescription", trainee.getEmployee().getRole().getDescription())
                    .uniqueResult();
            if (roleResult != null) {
                trainee.getEmployee().setRole(roleResult);
            }

            Qualification qualificationResult = (Qualification) session.createQuery("from Qualification where description = :description")
                    .setParameter("description", trainee.getEmployee().getQualification().getDescription())
                    .uniqueResult();
            if (qualificationResult != null) {
                trainee.getEmployee().setQualification(qualificationResult);
            }
            session.saveOrUpdate(trainee);
            transaction.commit();
        } catch (Throwable ex) {
            ex.printStackTrace();
        } finally {
            session.close();
        }
    }

    /**
     * <p>
     * This is method is used to Retrieve all Trainees from the Database using Hibernate
     * </p>
     *
     * @return - trainees
     * It returns all trainees from the List
     **/
    @Override
    public List<Trainee> retrieveTrainees() {
        logger.info("Entered retriveTrainees() method");
        List<Trainee> trainees = new ArrayList();
        try {
            session = factory.openSession();
            trainees = session.createQuery("from Trainee").list();
        } catch (Throwable ex) {
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return trainees;
    }

    /**
     * <p>
     * This method is used to Delete Trainee Object from the database using Hibernate
     * </p>
     *
     * @param traineeId Trainee id has to be passed to this method
     *                  to get deleted the Trainee Object
     **/
    @Override
    public boolean deleteTraineeById(int traineeId) {
        logger.info("Entered deleteTraineeById() method");
        boolean isDeleted = false;
        try {
            session = factory.openSession();
            Transaction transaction = session.beginTransaction();
            Trainee result = (Trainee) session.createQuery("from Trainee where employee.id = :id")
                    .setParameter("id", traineeId)
                    .uniqueResult();
            if (result != null) {
                session.remove(result);
                isDeleted = true;
            }
            transaction.commit();
        } catch (Throwable ex) {
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return isDeleted;
    }

    /**
     * <p>
     * This method is used to Retrieve the one Trainee Details from Database
     * Using Hibernate
     * </p>
     *
     * @param traineeId Trainee id has to be passed to this method to retrieve
     * @return trainee
     * This method will return trainee Object based on Trainee id
     **/
    @Override
    public Trainee retrieveTraineeById(int traineeId) {
        logger.info("Entered retriveTraineeById() method");
        Trainee trainee = null;
        try {
            session = factory.openSession();
            Transaction transaction = session.beginTransaction();
            Trainee result = (Trainee) session.createQuery("from Trainee where employee.id = :id")
                    .setParameter("id", traineeId)
                    .uniqueResult();
            if (result != null) {
                trainee = result;
            }
            transaction.commit();
        } catch (Throwable ex) {
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return trainee;
    }
}