package repository;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;

import entity.Creation;
import entity.User;
import util.HibernateUtil;

public class CreationRepository {
	public static void addCreation(Creation creation) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.save(creation);
		session.getTransaction().commit();
	}
	
	public static void modifyCreation(Creation creation,Long id) {
		try {
			Creation creationToModify = getCreationById(id);
			creationToModify.setProduct(creation.getProduct());
			creationToModify.setQuantity(creation.getQuantity());
			creationToModify.setUser(creation.getUser());
			
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			session.save(creationToModify);
			session.getTransaction().commit();
		}catch(Exception e) {
			System.out.println("erreur in CreationRepository : " + e.getMessage());
		}
	}

	public static Creation getCreationById(Long id) {
		Creation creation = null;
		try {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		creation = (Creation) session.get(Creation.class, id);
		session.getTransaction().commit();
		}catch(Exception e) {
			System.out.println("erreur in CreationRepository : " + e.getMessage());
		}
		return creation;
	}
	
	public static List<Creation> getAllCreationOfUser(Long idUser) {
		Transaction transaction = null;
		List<Creation> result = null;

	    try {
	        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	        transaction = session.beginTransaction();
	        String hql = "FROM Creation c LEFT JOIN FETCH c.user LEFT JOIN FETCH c.product p LEFT JOIN FETCH p.type WHERE c.user.id = :id";

	        Query query = session.createQuery(hql);
	        query.setParameter("id", idUser);

	        result = query.list();
	       
	        transaction.commit();
	    } catch (Exception e) {
	        // Rollback the transaction in case of an exception
	        if (transaction != null && transaction.isActive()) {
	            transaction.rollback();
	        }
	        System.out.println("Error in CreationRepository: " + e.getMessage());
	    }
		return result;
	}
	public static List<Creation> getAllCreationOtherUsers(Long idUser) {
		Transaction transaction = null;
		List<Creation> result = null;
	    try {
	        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	        transaction = session.beginTransaction();
	        // Using named parameters in HQL
	        String hql = "FROM Creation c LEFT JOIN FETCH c.user LEFT JOIN FETCH c.product p LEFT JOIN FETCH p.type WHERE c.user.id != :id";

	        Query query = session.createQuery(hql);
	        query.setParameter("id", idUser);
	        result = query.list();
	        transaction.commit();
	    } catch (Exception e) {
	        // Rollback the transaction in case of an exception
	        if (transaction != null && transaction.isActive()) {
	            transaction.rollback();
	        }
	        System.out.println("Error in CreationRepository: " + e.getMessage());
	    }
		return result;
	}
}
