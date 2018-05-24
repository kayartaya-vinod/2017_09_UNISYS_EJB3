package training.web.backend;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import training.dao.DaoException;
import training.dao.OrderDao;
import training.entity.Order;

@WebServlet("/backend/view-orders")
public class ViewOrdersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	OrderDao dao;

	@Resource(lookup = "java:/ConnectionFactory")
	ConnectionFactory connFactory;

	@Resource(lookup = "java:/OrderStatusChangedTopic")
	Topic topic;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			String orderId = request.getParameter("order_id");
			String status = request.getParameter("status");

			String messageToken = orderId + ";" + status;
			// send this messageToken to javax.jms.Topic -
			// "java:/OrderStatusChangedTopic"

			Connection conn = connFactory.createConnection();
			Session session = conn.createSession(Session.AUTO_ACKNOWLEDGE);
			MessageProducer sender = session.createProducer(topic);

			TextMessage msg = session.createTextMessage();
			msg.setText(messageToken);
			
			conn.start();
			
			System.out.println("Sending the messageToken to its destination...");
			sender.send(msg);
			System.out.println("messageToken sent to its destination!");
			
			conn.stop();
			conn.close();
			
			// clientside rediection to the same servlet, but GET method
			response.sendRedirect(request.getRequestURI());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			List<Order> orders = dao.getAllOrders();
			request.setAttribute("orders", orders);
			request.getRequestDispatcher("/WEB-INF/pages/backend/orders.jsp").forward(request, response);
		} catch (DaoException e) {
			throw new ServletException(e);
		}
	}
}
