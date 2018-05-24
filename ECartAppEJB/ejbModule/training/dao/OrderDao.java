package training.dao;

import java.util.List;

import javax.ejb.Remote;

import training.entity.Order;

@Remote
public interface OrderDao {

	public Integer addOrder(Order order) throws DaoException;

	public Order getOrder(Integer id) throws DaoException;

	public void updateOrder(Order order) throws DaoException;

	public List<Order> getAllOrders() throws DaoException;
}
