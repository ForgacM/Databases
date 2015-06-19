package dao;

/**
 * Created by marcelforgac on 1.4.15.
 */

import dbUtil.DbUtil;
import dbUtil.HibernateUtil;
import entity.SessionEntity;
import model.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class UserSessionDao {

	private Connection connection = null;

	public UserSessionDao() {
		connection = DbUtil.getConnection();
	}


	public void loginUser(User user) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			SessionEntity sessionEntity = new SessionEntity(user.getUserId());
			session.save(sessionEntity);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}
	}

	public void logoutUser(User user) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			Query query = session.createQuery("from entity.SessionEntity where patientId = :patientId");
			query.setInteger("patientId", user.getUserId());
			List<SessionEntity> list = query.list();
			for (SessionEntity sessionEntity : list) {
					session.delete(sessionEntity);
			}
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}
	}

	public int getLoggedUserId() {
		int userId = -1;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			Query query = session.createQuery("from entity.SessionEntity");
			List<SessionEntity> list = query.list();
			for (SessionEntity sessionEntity : list) {
				userId = sessionEntity.getPatientId();
			}
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}
		return userId;
	}

	public void deleteSession() {

		PreparedStatement preparedStatement;
		try {
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement("DELETE FROM session");
		preparedStatement.executeUpdate();
			preparedStatement = connection.prepareStatement("UPDATE drugs SET state=0 WHERE state=1");
			preparedStatement.executeUpdate();
			preparedStatement = connection.prepareStatement("TRUNCATE TABLE session");
			preparedStatement.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

}
