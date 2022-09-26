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

import com.ideas2it.employee.models.Trainer;
import com.ideas2it.employee.models.Role;
import com.ideas2it.employee.models.Qualification;
import com.ideas2it.employee.dao.inter.TrainerDao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.cfg.Configuration;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class TrainerDaoImpl implements TrainerDao {

    private SessionFactory factory = new Configuration().configure().buildSessionFactory();
    private Session session;

    /**
     * <p>
     * This method is used to Insert or Update Trainer details into database using Hibernate
     * </p>
     *
     * @param trainer trainer object has to be passed to store
     **/
    public void insertOrUpdateTrainer(Trainer trainer) {
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
    public List<Trainer> retrieveTrainers() {
        List<Trainer> trainers = new ArrayList<>();
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
    public boolean deleteTrainerById(int trainerId) {
        boolean isDeleted = false;
        try {

            session = factory.openSession();
            Transaction transaction = session.beginTransaction();
            Trainer result = (Trainer) session.createQuery("from Trainer where employee.id = :id")
                    .setParameter("id", trainerId)
                    .uniqueResult();
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
    public Trainer retrieveTrainerById(int trainerId) {
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
}