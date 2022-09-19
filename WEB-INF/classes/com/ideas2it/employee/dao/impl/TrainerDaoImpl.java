/**
 * <p>
 * TrainerDao class - This is DAO class which has List that store trainer objects,
 * It also has methods to do CRUD operations on List
 * Insert, Retrieve, Update, Delete
 * </p>
 * @author Mohammed Ammar
 * @version 1.0
 * @since 12/08/2022
 *
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
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

public class TrainerDaoImpl implements TrainerDao {

	private SessionFactory factory;
	private Session session;

	/**
	 * <p>
	 * This method is used to Insert Trainer details into database using Hibernate
	 * </p>
	 *
	 * @param trainer trainer object has to be passed to store
	 **/
	public void insertTrainer(Trainer trainer) {
		try {
			factory = new Configuration().configure().buildSessionFactory();
			session = factory.openSession();
			Transaction transaction = session.beginTransaction();
			Criteria criteria = session.createCriteria(Role.class);
			criteria.add(Restrictions.eq("description", trainer.getEmployee().getRole().getDescription()));
			Role roleResult = (Role) criteria.uniqueResult();
			if (roleResult != null) {
				trainer.getEmployee().setRole(roleResult);
			}
			criteria = session.createCriteria(Qualification.class);
			criteria.add(Restrictions.eq("description", trainer.getEmployee().getQualification().getDescription()));
			Qualification qualificationResult = (Qualification) criteria.uniqueResult();
			if (qualificationResult != null) {
				trainer.getEmployee().setQualification(qualificationResult);
			}
			session.save(trainer);
			transaction.commit();
		} catch(Throwable ex) {
			ex.printStackTrace();
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
			factory = new Configuration().configure().buildSessionFactory();
			session = factory.openSession();
			Transaction transaction = session.beginTransaction();
			trainers = session.createQuery("from Trainer").list();
			transaction.commit();
		} catch(Throwable ex) {
			ex.printStackTrace();
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
			factory = new Configuration().configure().buildSessionFactory();
			session = factory.openSession();
			Transaction transaction = session.beginTransaction();
			Trainer results = (Trainer) session.createCriteria(Trainer.class).add(Restrictions.eq("employee.id", trainerId)).uniqueResult();
			if (results != null) {
				session.remove(results);
			}
			transaction.commit();
			isDeleted = true;
		} catch(Throwable ex) {
			ex.printStackTrace();
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
			factory = new Configuration().configure().buildSessionFactory();
			session = factory.openSession();
			Transaction transaction = session.beginTransaction();
			Trainer results = (Trainer) session.createCriteria(Trainer.class).add(Restrictions.eq("employee.id", trainerId)).uniqueResult();
			if (results != null) {
				trainer = results;
			}
			transaction.commit();
		} catch(Throwable ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return trainer;
	}


	/**
	 * <p>
	 * This method is used to Update Trainer object on Database using Hibernate
	 * </p>
	 * @param trainer
	 *		object has to passed to perform Update operation
	 * @return isUpdated
	 * 		This method will Old Object
	 **/
	public boolean updateTrainer(Trainer trainer) {
		boolean isUpdated = false;
		try {
			factory = new Configuration().configure().buildSessionFactory();
			session = factory.openSession();
			Transaction transaction = session.beginTransaction();
			session.merge(trainer);
			transaction.commit();
			isUpdated = true;
		} catch(Throwable ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return isUpdated;
	}
}