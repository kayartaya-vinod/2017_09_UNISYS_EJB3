package training.dao;

import java.util.List;

import javax.ejb.Remote;

import training.entity.Brand;
import training.entity.Category;
import training.entity.Product;

@Remote
public interface ProductDao {

	// CRUD OPERATIONS
	public void addProduct(Product product) throws DaoException;

	public Product getProduct(Integer id) throws DaoException;

	public void updateProduct(Product product) throws DaoException;

	// QUERIES

	public List<Product> getAllProducts(Integer pageNum) throws DaoException;

	public List<Product> getProductsByCategory(Integer categoryId) throws DaoException;

	public List<Product> getProductsByBrand(Integer brandId) throws DaoException;

	public List<Brand> getAllBrands() throws DaoException;

	public List<Category> getAllCategories() throws DaoException;

}
