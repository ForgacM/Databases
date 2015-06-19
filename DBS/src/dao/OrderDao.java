package dao;

import dbUtil.HibernateUtil;
import entity.ItemEntity;
import entity.OrderEntity;
import entity.PatientEntity;
import model.Order;
import model.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by marcelforgac on 2.4.15.
 */
public class OrderDao {


	public void createOrder(String date, int serial, User currentUser) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			OrderEntity orderEntity = new OrderEntity(date, serial, currentUser.getUserId());
			session.save(orderEntity);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}
	}

	public int getOrderIdBySerialNumber(Integer serial) {
		int result = 0;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			Query query = session.createQuery("from entity.OrderEntity where serialNumber =:serial");
			query.setInteger("serial",serial);
			List<OrderEntity> orderList = query.list();
			for (OrderEntity ord : orderList) {
				result = ord.getOrderId();
			}
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}
		return result;
	}


	public ArrayList<Order> getOrderByPatientName(String name) {
		ArrayList<Order> orderArray = new ArrayList();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			int patId = -2;
			Query query = session.createQuery("from entity.PatientEntity where name =:name");
			query.setString("name", name);
			List<PatientEntity> patientList = query.list();
			for (Iterator iterator = patientList.iterator(); iterator.hasNext();) {
				PatientEntity pat = (PatientEntity) iterator.next();
				patId = pat.getPatientId();
			}
			if (patId > -2){
				Query querys = session.createQuery("from entity.OrderEntity where patientId =:patId");
				querys.setInteger("patId", patId);
				List<OrderEntity> orderList = querys.list();
				for (Iterator iterator = orderList.iterator(); iterator.hasNext();) {
					Order order = new Order();
					OrderEntity ord = (OrderEntity) iterator.next();
					order.setDateTime(ord.getDate());
					order.setSerialNumber(ord.getSerialNumber());
					order.setOrderId(ord.getOrderId());
					orderArray.add(order);
				}
			}
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}
		return orderArray;
	}


	public void deleteOrder(Integer orderID) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			Query query = session.createQuery("from entity.ItemEntity where orderId =:orderID");
			query.setInteger("orderID", orderID);
			List<ItemEntity> orderList = query.list();
			for (ItemEntity item : orderList) {
				ItemEntity itemEntity = (ItemEntity) session.get(ItemEntity.class, item.getItemsId());
				if (itemEntity != null) {
					session.delete(itemEntity);
				}
			}
			OrderEntity orderEntity = (OrderEntity) session.get(OrderEntity.class, orderID);
			session.delete(orderEntity);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}
	}
}
