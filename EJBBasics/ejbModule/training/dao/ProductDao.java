package training.dao;

import training.entity.Product;

public interface ProductDao {
	
	public int getProductCount();

	public Product getProduct(int id);
	
}
