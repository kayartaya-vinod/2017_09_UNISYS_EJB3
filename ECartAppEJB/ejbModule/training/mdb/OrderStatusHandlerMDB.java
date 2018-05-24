package training.mdb;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.mail.Session;

import training.dao.OrderDao;
import training.entity.Order;

@MessageDriven(name = "OrderStatusHandlerMDB", activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "java:/OrderStatusChangedTopic") })
public class OrderStatusHandlerMDB implements MessageListener {

	@EJB
	OrderDao orderDao;

	@Resource(lookup = "java:/ecartMailSession")
	Session emailSession;
	
	@PostConstruct
	public void test(){
		System.out.println("emailSession is " + emailSession);
	}

	// this method is automatically invoked by the EJB container, whenever
	// there is a new message for this Bean
	@Override
	public void onMessage(Message message) {
		try {
			if (message instanceof TextMessage) {
				TextMessage tm = (TextMessage) message;
				String text = tm.getText();
				// order-id;status
				// 123;PROCESSING
				// 123;DECLINED
				// 123;DISPATCHED
				String[] ar = text.split(";");
				Integer orderId = new Integer(ar[0]);
				String status = ar[1];
				
				// get the order object
				Order order = orderDao.getOrder(orderId);
				// update the status
				order.setStatus(status);
				// update the order object back to the db
				orderDao.updateOrder(order);
				
				System.out.println(">>>> order status has been updated");
				System.out.println(">>>> Id = " + orderId);
				System.out.println(">>>> Status = " + status);
				// send an email to the customer
				// TO BE DONE LATER
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}



