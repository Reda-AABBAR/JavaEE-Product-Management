package repository;


import java.util.List;

import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;

import entity.User;
import util.HibernateUtil;

public class UserRepository {
	public static void addUser(User user) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.save(user);
		session.getTransaction().commit();
	}
	
	public static void modifyUser(User user,Long id) {
		try {
			User userToModify = getUserById(id);
			userToModify.setCreation(user.getCreation());
			userToModify.setEmail(user.getEmail());
			userToModify.setFirstName(user.getFirstName());
			userToModify.setLastName(user.getLastName());
			userToModify.setPassword(user.getPassword());
			
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			session.save(user);
			session.getTransaction().commit();
		}catch(Exception e) {
			System.out.println("erreur in UserRepository : " + e.getMessage());
		}
	}

	public static User getUserById(Long id) {
		User user = null;
		try {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		user = (User) session.get(User.class, id);
		session.getTransaction().commit();
		}catch(Exception e) {
			System.out.println("erreur in UserRepository : " + e.getMessage());
		}
		return user;
	}
	
	public static User getUserByLogin(String email, String password) {
		System.out.println("email : " + email + "password : " + password);
	    User user = null;
	    Transaction transaction = null;

	    try {
	        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	        transaction = session.beginTransaction();

	        // Using named parameters in HQL
	        String hql = "FROM User WHERE email = :email AND password = :password";

	        Query query = session.createQuery(hql);
	        query.setParameter("email", email);
	        query.setParameter("password", password);

	        List<User> result = query.list();
	        if (!result.isEmpty()) {
	            user = (User) result.get(0);
	        }else
	        	 System.out.println("empty ");

	        transaction.commit();
	    } catch (Exception e) {
	        // Rollback the transaction in case of an exception
	        if (transaction != null && transaction.isActive()) {
	            transaction.rollback();
	        }
	        System.out.println("Error in UserRepository: " + e.getMessage());
	    }

	    return user;
	}

}
