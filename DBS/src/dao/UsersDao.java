package dao;

import dbUtil.HibernateUtil;
import entity.PatientEntity;
import model.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Iterator;
import java.util.List;

/**
 * Created by marcelforgac on 1.4.15.
 */
public class UsersDao {


	public User getUserByName(String name) {

		User user = new User();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {

			Query query = session.createQuery("from entity.PatientEntity where name =:name");
			query.setString("name", name);
			List<PatientEntity> patientList = query.list();
			for (Iterator iterator = patientList.iterator(); iterator.hasNext(); ) {
				PatientEntity pat = (PatientEntity) iterator.next();
				user.setUserId(pat.getPatientId());
				user.setFirstName(pat.getName());
				user.setLastName(pat.getLastName());
				user.setPoistovna(pat.getPoistovna());
			}
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}
		return user;
	}

	public void addUser(User user) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			PatientEntity patientEntity = new PatientEntity(user.getFirstName(), user.getLastName(),user.getPoistovna());
			session.save(patientEntity);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}
	}


	public User getUserById(int userId) {

		User user = new User();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("from entity.PatientEntity where patientId =:userId");
		query.setInteger("userId", userId);
		List<PatientEntity> patientList = query.list();
		try {
			for (Iterator iterator = patientList.iterator(); iterator.hasNext(); ) {
				PatientEntity pat = (PatientEntity) iterator.next();
				user.setUserId(pat.getPatientId());
				user.setFirstName(pat.getName());
				user.setLastName(pat.getLastName());
				user.setPoistovna(pat.getPoistovna());
			}
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}
		return user;
	}

}
