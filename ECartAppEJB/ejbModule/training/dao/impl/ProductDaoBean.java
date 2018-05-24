package training.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import training.dao.DaoException;
import training.dao.ProductDao;
import training.entity.Brand;
import training.entity.Category;
import training.entity.Product;

@Stateless
@SuppressWarnings("unchecked")
public class ProductDaoBean implements ProductDao {

	@PersistenceContext(unitName = "TrainingPU")
	EntityManager em;

	public ProductDaoBean() {
	}

	@Override
	public void addProduct(Product product) throws DaoException {
		try {
			em.persist(product);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public Product getProduct(Integer id) throws DaoException {
		try {
			return em.find(Product.class, id);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public void updateProduct(Product product) throws DaoException {
		try {
			em.merge(product);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public List<Product> getAllProducts(Integer pageNum) throws DaoException {
		try {
			int offset = (pageNum - 1) * 20;
			return em.createQuery("from Product")
					.setFirstResult(offset)
					.setMaxResults(20)
					.getResultList();
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public List<Product> getProductsByCategory(Integer categoryId) throws DaoException {
		try {
			return em.createQuery("select p from Product p where p.category.id = ?").setParameter(1, categoryId)
					.getResultList();
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public List<Product> getProductsByBrand(Integer brandId) throws DaoException {
		try {
			return em.createQuery("select p from Product p where p.brand.id = :BRAND_ID")
					.setParameter("BRAND_ID", brandId)
					.getResultList();
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public List<Brand> getAllBrands() throws DaoException {
		try {
			return em.createQuery("from Brand").getResultList();
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public List<Category> getAllCategories() throws DaoException {
		try {
			return em.createQuery("from Category").getResultList();
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

}
