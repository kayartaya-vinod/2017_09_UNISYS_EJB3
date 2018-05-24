package training.dao.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import training.dao.CustomerDao;
import training.dao.DaoException;
import training.entity.Customer;

@Stateless
public class CustomerDaoBean implements CustomerDao {

	@PersistenceContext(unitName = "TrainingPU")
	EntityManager em;

	@Override
	public void addCustomer(Customer customer) throws DaoException {
		try {
			em.persist(customer);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public void updateCustomer(Customer customer) throws DaoException {
		try {
			em.merge(customer);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public Customer getCustomer(Integer id) throws DaoException {
		try {
			return em.find(Customer.class, id);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public Customer getCustomer(String email, String password) throws DaoException {
		try {
			return (Customer) em.createQuery("from Customer where email=:EMAIL and password=:PWD")
					.setParameter("EMAIL", email)
					.setParameter("PWD", password)
					.getSingleResult();
		} 
		catch(NoResultException e){
			return null;
		}
		catch (Exception e) {
			throw new DaoException(e);
		}
	}

}
