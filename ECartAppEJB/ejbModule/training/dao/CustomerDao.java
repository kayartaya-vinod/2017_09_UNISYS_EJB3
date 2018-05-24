package training.dao;

import javax.ejb.Remote;

import training.entity.Customer;

@Remote
public interface CustomerDao {
	public void addCustomer(Customer customer) throws DaoException;

	public void updateCustomer(Customer customer) throws DaoException;

	public Customer getCustomer(Integer id) throws DaoException;
	
	public Customer getCustomer(String email, String password) throws DaoException;
}
