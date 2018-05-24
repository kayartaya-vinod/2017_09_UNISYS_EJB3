package training.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import training.dao.JpaTester;
import training.entity.Brand;
import training.entity.Customer;
import training.entity.Product;

@SuppressWarnings("unchecked")
@Stateless
public class JpaTesterImpl implements JpaTester {

	@PersistenceContext(unitName = "TrainingPU")
	EntityManager em;

	public JpaTesterImpl() {
	}

	@Override
	public void testJpaFeature() {
		// getOneProductById(22);
		// insertNewProduct();
		// increaseProductPriceBy(86, 10.0);
		// deleteProductById(86);
		// getBrandAndProducts(1);
		// finAllBrands();
		// getProductsByPriceRange(25.0, 30.0);
		// getProductsByPageNumber(3);
		 getCustomer(1);
	}

	

	void getCustomer(int id) {
		Customer c1 = em.find(Customer.class, id);
		System.out.println(c1);
	}

	void getProductsByPageNumber(int pageNum) {
		int pageSize = 10;
		int offset = (pageNum - 1) * pageSize;

		String jpql = "from Product";
		Query qry = em.createQuery(jpql);
		qry.setFirstResult(offset);
		qry.setMaxResults(pageSize);
		List<Product> list = qry.getResultList();

		for (Product p : list) {
			System.out.printf("%d --> %s\n", p.getId(), p.getName());
		}
	}

	void getProductsByPriceRange(double min, double max) {
		String jpql = "from Product where unitPrice between ? and ? order by unitPrice desc";
		Query qry = em.createQuery(jpql);
		qry.setParameter(1, min);
		qry.setParameter(2, max);
		List<Product> list = qry.getResultList();

		for (Product p : list) {
			System.out.printf("%s --> %.2f\n", p.getName(), p.getUnitPrice());
		}

	}

	void finAllBrands() {
		String jpql = "select b from Brand b";
		Query qry = em.createQuery(jpql);
		List<Brand> list = qry.getResultList();

		for (Brand b : list) {
			System.out.printf("The brand '%s' has %d products\n", b.getName(), b.getProducts().size());
		}
	}

	void getBrandAndProducts(int brandId) {
		Brand b = em.find(Brand.class, brandId);
		System.out.println("Brand name: " + b.getName());
		System.out.printf("%d Products in this brand are:\n", b.getProducts().size());
		for (Product p : b.getProducts()) {
			System.out.printf("%d --> %s\n", p.getId(), p.getName());
		}
	}

	void deleteProductById(int id) {
		// Product p = em.find(Product.class, id);
		Product p = new Product();
		p.setId(id);
		em.remove(p);
		System.out.println("The removed object is: " + p);
	}

	void increaseProductPriceBy(int id, double inc) {
		Product p = em.find(Product.class, id);
		System.out.println("Product price before update is Rs." + p.getUnitPrice());

		p.setUnitPrice(p.getUnitPrice() + inc);
		em.merge(p);

		p = em.find(Product.class, id);
		System.out.println("Product price update update is Rs." + p.getUnitPrice());
	}

	void insertNewProduct() {
		Product p = new Product();
		p.setName("Test product");
		p.setDescription("Test description");
		p.setUnitPrice(100.9);
		p.setDiscount(10.0);
		p.setQuantityPerUnit("Test quantity per unit");

		em.persist(p);
		System.out.println("The product saved in the DB with id " + p.getId());
	}

	void getOneProductById(int id) {
		Product p = em.find(Product.class, id);
		System.out.println("Name = " + p.getName());
		System.out.println("Price = Rs." + p.getUnitPrice());
		System.out.println("Brand = " + p.getBrand().getName());

	}
}
