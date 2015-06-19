package dao;

import dbUtil.HibernateUtil;
import entity.DrugEntity;
import entity.DrugTypeEntity;
import entity.ItemEntity;
import model.Drug;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by marcelforgac on 1.4.15.
 */
public class DrugDao {


	public ArrayList<Drug> getAllDrugs() {
		ArrayList<Drug> drugs = new ArrayList();

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {

			List drugList = session.createQuery("from entity.DrugEntity").list();
			for (Iterator iterator = drugList.iterator(); iterator.hasNext();) {
				DrugEntity dre = (DrugEntity) iterator.next();
				Drug drug = new Drug();
				drug.setDrugId(dre.getDrugId());
				drug.setName(dre.getName());
				drug.setCount(dre.getCount());
				drug.setState(dre.getState());
				drug.setDrugTypeId(dre.getDrugTypeId());
				DrugTypeEntity drugTypeEntity = dre.getDrugType();
				drug.setDrug_type(drugTypeEntity.getTypLieku());
				drugs.add(drug);
			}
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}
		return drugs;
	}

	public String getDrugNameById(int id) {
		List<Drug> list = null;

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {

			Criteria criteria = session.createCriteria(DrugEntity.class)
					.add(Restrictions.eq("drugId", id));
			list = criteria.list();

			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}
		return list.get(0).getName();
	}


	public ArrayList<String> getDrugTypes() {
		ArrayList<String> typeList = new ArrayList();

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			Query query = session.createQuery("from entity.DrugTypeEntity");
			List<DrugTypeEntity> list = query.list();
			for (DrugTypeEntity drugTypeEntity : list) {
				typeList.add(drugTypeEntity.getTypLieku());
			}
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		}
		return typeList;
	}

	public void updateState(Drug drug) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			DrugEntity drugEntity = (DrugEntity)session.get(DrugEntity.class, drug.getDrugId());
			drugEntity.setState(drug.isState());
			session.update(drugEntity);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}

	}


	public void deleteDrug(Drug drug) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			Query query = session.createQuery("from entity.DrugEntity where state =:stat");
			query.setBoolean("stat", drug.isState());
			List<DrugEntity> drugList = query.list();
			for (DrugEntity dre : drugList) {
				ItemEntity itemEntity = (ItemEntity)  session.get(ItemEntity.class, dre.getDrugId());
				if (itemEntity != null) {
					session.delete(itemEntity);
				}
				DrugEntity drugEntity = (DrugEntity) session.get(DrugEntity.class, dre.getDrugId());
				session.delete(drugEntity);
			}
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}
	}

	public void addNewDrug(Drug drug) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			DrugEntity drugEntity = new DrugEntity(drug.getName(),drug.getCount(), drug.getTypeIndex(), drug.isRecept(), false);
			session.save(drugEntity);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}
	}
}
