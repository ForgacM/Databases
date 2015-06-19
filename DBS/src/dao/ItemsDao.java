package dao;

import dbUtil.DbUtil;
import dbUtil.HibernateUtil;
import entity.DrugEntity;
import entity.DrugTypeEntity;
import entity.ItemEntity;
import model.Drug;
import model.Items;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by marcelforgac on 2.4.15.
 */
public class ItemsDao {

	private DrugDao drug = new DrugDao();

	public void orderItems(Drug drug, int orderId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			ItemEntity itemEntity = new ItemEntity(orderId, drug.getDrugId(), 1);
			session.save(itemEntity);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}
	}

	public ArrayList<Items> getAllItemsByOrder(int orderId) {
		ArrayList<Items> itemList = new ArrayList();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			org.hibernate.Query query = session.createQuery("from ItemEntity where orderId =:orderId");
			query.setInteger("orderId", orderId);
			List<ItemEntity> itemEntityList = query.list();

			for (Iterator iterator = itemEntityList.iterator(); iterator.hasNext();) {
				ItemEntity itemEntity = (ItemEntity) iterator.next();
				Items items = new Items();
				items.setItemId(itemEntity.getItemsId());
				items.setCount(itemEntity.getPocetKusov());
				DrugEntity drugEntity = itemEntity.getItem_drug();
				items.setName(drugEntity.getName());
				DrugTypeEntity drugTypeEntity = drugEntity.getDrugType();
				items.setDrug_type(drugTypeEntity.getTypLieku());
				itemList.add(items);
			}
			tx.commit();
		}catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}
	return itemList;
	}

	public String getStat() {
		List<DrugEntity> drugList = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			Transaction tx = session.beginTransaction();
		try {
			List<Object> list = session.createCriteria(ItemEntity.class)
					.setProjection(Projections.projectionList()
							.add(Projections.groupProperty("item_drug.drugId"))
							.add(Projections.alias(Projections.count("item_drug"), "count")))
					.addOrder(org.hibernate.criterion.Order.desc("count")).setMaxResults(1).list();
			int avg = 0;
			int iter = 0;
			for (Iterator it = list.iterator(); it.hasNext();){
				Object[] obj = (Object[]) it.next();
				Criteria criteria = session.createCriteria(DrugEntity.class)
						.add(Restrictions.eq("drugId", Integer.decode(obj[0].toString())));
				drugList = criteria.list();
				tx.commit();
				return drugList.get(0).getName();
			}
		}
		catch (Exception e){
			e.printStackTrace();
			tx.rollback();
			return null;
		}
		return null;
	}

	public Float getAvgFromItems() {
		float average = 0;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			Transaction tx = session.beginTransaction();
		try {
			List<Object> list = session.createCriteria(ItemEntity.class)
					.setProjection(Projections.projectionList()
							.add(Projections.groupProperty("orderId"))
							.add(Projections.alias(Projections.count("orderId"), "counts"))).list();
			int avg = 0;
			int iter = 0;
			for (Iterator it = list.iterator(); it.hasNext();){
				Object[] obj = (Object[]) it.next();
				avg += Integer.decode((String) obj[1].toString());
				iter++;
			}
			average = avg/iter;
			tx.commit();
		}
		catch (Exception e){
			e.printStackTrace();
			tx.rollback();
		}
		return average;
	}
	//end of class
}