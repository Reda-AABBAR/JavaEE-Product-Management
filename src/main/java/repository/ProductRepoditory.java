package repository;

import org.hibernate.classic.Session;

import entity.Product;
import util.HibernateUtil;

public class ProductRepoditory {
	public static Product addProduct(Product product) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Long productId = (Long)session.save(product);
		session.getTransaction().commit();
		Product newProduct = getProductById(productId);
		return newProduct;
	}
	
	public static void modifyProduct(Product product,Long id) {
		try {
			Product productToModify = getProductById(id);
			productToModify.setName(product.getName());
			productToModify.setType(product.getType());
			productToModify.setCreation(product.getCreation());
			
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			session.save(product);
			session.getTransaction().commit();
		}catch(Exception e) {
			System.out.println("erreur in ProductRepository : " + e.getMessage());
		}
	}

	public static Product getProductById(Long id) {
		Product product = null;
		try {
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			product = (Product) session.get(Product.class, id);
			session.getTransaction().commit();
		}catch(Exception e) {
			System.out.println("erreur in ProductRepository : " + e.getMessage());
		}
		return product;
	}
}
