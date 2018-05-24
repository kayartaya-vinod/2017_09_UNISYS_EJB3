package com.unisys.tests;

import java.util.List;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;

import training.dao.OrderDao;
import training.entity.Order;

public class UpdateOrderStatus {

	public static void main(String[] args) throws Exception {

		String jndiName = "ejb:ECartApp/ECartAppEJB/OrderDaoBean!training.dao.OrderDao";
		Context ctx;
		Properties props = new Properties();
		props.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
		props.put(Context.SECURITY_PRINCIPAL, "john");
		props.put(Context.SECURITY_CREDENTIALS, "admin");

		ctx = new InitialContext(props);
		OrderDao dao = (OrderDao) ctx.lookup(jndiName);

		System.out.println("dao is an instanceof " + dao.getClass());

		List<Order> list = dao.getAllOrders();

		System.out.println("Order count = " + list.size());

		Order order = dao.getOrder(1);
		System.out.println("Order 1 was placed by the customer " + order.getCustomer().getName());
		System.out.println("Order 1 status is " + order.getStatus());

		ctx.close();

	}
}
