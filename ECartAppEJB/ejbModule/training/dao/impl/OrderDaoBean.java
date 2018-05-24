package training.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import training.dao.DaoException;
import training.dao.OrderDao;
import training.entity.Order;

@Stateless
public class OrderDaoBean implements OrderDao {

	@PersistenceContext(unitName = "TrainingPU")
	EntityManager em;

	@Override
	public Integer addOrder(Order order) throws DaoException {
		try {
			em.persist(order); // will persist the contained LineItem's
			return order.getId();
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public Order getOrder(Integer id) throws DaoException {
		try {
			// gets only the order (and customer data), but not the line-items
			// may result in error in the JSP, will fix that when we encounter
			return em.find(Order.class, id);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public void updateOrder(Order order) throws DaoException {
		try {
			em.merge(order);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Order> getAllOrders() throws DaoException {
		try {
			return em.createQuery("from Order").getResultList();
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

}









