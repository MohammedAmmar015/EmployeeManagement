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

import com.ideas2it.employee.models.Trainee;
import com.ideas2it.employee.models.Role;
import com.ideas2it.employee.models.Qualification;
import com.ideas2it.employee.dao.inter.TraineeDao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.cfg.Configuration;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;


public class TraineeDaoImpl implements TraineeDao {

    private SessionFactory factory = new Configuration().configure().buildSessionFactory();
    private Session session;

    /**
     * <p>
     * This method is used to Insert or Update trainee details into database using Hibernate
     * </p>
     *
     * @param trainee trainee object has to be passed to store
     **/
    public void insertOrUpdateTrainee(Trainee trainee) {
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
    public List<Trainee> retrieveTrainees() {
        List<Trainee> trainees = new ArrayList<>();
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
    public boolean deleteTraineeById(int traineeId) {
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
    public Trainee retrieveTraineeById(int traineeId) {
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