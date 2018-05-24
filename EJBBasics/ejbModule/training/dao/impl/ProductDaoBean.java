package training.dao.impl;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import training.dao.ProductDao;
import training.entity.Product;

@Stateless
@Remote(ProductDao.class)
public class ProductDaoBean {

	@PersistenceContext(unitName = "TrainingPU")
	EntityManager em;

	public int getProductCount() {
		String sql = "select count(*) from products";
		Query qry = em.createNativeQuery(sql);
		Object result = qry.getSingleResult();
		return new Integer(result.toString());
	}
	
	public Product getProduct(int id){
		return em.find(Product.class, id);
	}

}
