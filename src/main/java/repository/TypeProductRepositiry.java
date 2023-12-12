package repository;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.classic.Session;

import entity.TypeProduit;
import util.HibernateUtil;

public class TypeProductRepositiry {
	public static void addTypeProduct(TypeProduit typeProduct) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.save(typeProduct);
		session.getTransaction().commit();
	}
	
	public static void modifyTypeProduit(TypeProduit typeProduct,Long id) {
		try {
			TypeProduit typeProductToModify = getTypeProductById(id);
			typeProductToModify.setTypeName(typeProduct.getTypeName());
			
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			session.save(typeProduct);
			session.getTransaction().commit();
		}catch(Exception e) {
			System.out.println("erreur in TypeProductRepository : " + e.getMessage());
		}
	}

	public static TypeProduit getTypeProductById(Long id) {
		TypeProduit typeProduit = null;
		try {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		typeProduit = (TypeProduit) session.get(TypeProduit.class, id);
		session.getTransaction().commit();
		}catch(Exception e) {
			System.out.println("erreur in TypeProductRepository : " + e.getMessage());
		}
		return typeProduit;
	}
	
	public static List<TypeProduit> getAllTypeProduct() {
		List<TypeProduit> typeProduits = null;
		try {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query query = session.createQuery("From TypeProduit");
		typeProduits = query.list();
		session.getTransaction().commit();
		}catch(Exception e) {
			System.out.println("erreur in TypeProductRepository : " + e.getMessage());
		}
		return typeProduits;
	}
}
