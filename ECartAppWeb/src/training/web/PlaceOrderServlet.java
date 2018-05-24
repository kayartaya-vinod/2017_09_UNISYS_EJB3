package training.web;

import java.io.IOException;
import java.util.Map;
import java.util.ResourceBundle;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.mail.MailSessionDefinition;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import training.dao.OrderDao;
import training.entity.Customer;
import training.entity.LineItem;
import training.entity.Order;

@MailSessionDefinition(name = "java:/ecartMailSession", host = "smtp.gmail.com", transportProtocol = "smtp", properties = {
		"mail.debug=true", "mail.smtp.ssl.enable=true", "mail.smtp.auth=true" })
@WebServlet(urlPatterns = "/place-order", loadOnStartup = 1)
public class PlaceOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	OrderDao dao;

	@Resource(lookup = "java:/ecartMailSession")
	Session session;
	// the above variable is used for creating:
	// 1. a Message object
	// 2. a Transport object using which we can send the message

	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			// check if the user has logged in
			// if not logged in , redirect to login page

			Customer customer = (Customer) request.getSession().getAttribute("customer");
			if (customer == null) {
				request.getSession().setAttribute("URL_TO_REDIRECT", request.getRequestURI());
				response.sendRedirect("./login-register");
				return;
			}

			Map<String, LineItem> cart = (Map<String, LineItem>) request.getSession().getAttribute("cart");

			if (cart == null || cart.size() == 0) {
				response.sendRedirect("./");
				// we will change this to order-history-page
				return;
			}

			// if logged in,
			// create an Order entity object,
			Order order = new Order(); // new entity object
			order.setCustomer(customer);

			// add all line items to the Order
			for (LineItem item : cart.values()) {
				order.addLineItem(item);
			}
			Integer orderId = dao.addOrder(order);
			order.setId(orderId);

			// empty the cart
			request.getSession().removeAttribute("cart");

			// redirect the user to a order-summary-page
			request.setAttribute("order", order);
			request.getRequestDispatcher("/WEB-INF/pages/order-summary.jsp").forward(request, response);

			// get the email credentials from the file "email.properties"
			ResourceBundle rb = ResourceBundle.getBundle("email");
			String username = rb.getString("username");
			String password = rb.getString("password");
			
			// compose and send the mail
			// part 1 : Mail message
			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(username));
			msg.setRecipient(RecipientType.TO, new InternetAddress(customer.getEmail()));
			msg.setRecipient(RecipientType.CC, new InternetAddress("vinod@knowledgeworksindia.com"));
			msg.setSubject("eCart - Your order summary");
			
			StringBuffer sb = new StringBuffer(1000);
			sb.append("Hello, " + customer.getName() + "!");
			sb.append("\n\n\n\n");
			sb.append("Your order has been placed successfully!");
			sb.append("\n\n");
			sb.append("Order id: " + order.getId());
			sb.append("\n");
			sb.append("Status: " + order.getStatus());
			sb.append("\n\n\n\n");
			sb.append("To view the order details, please visit the website and login.");
			sb.append("\n\n\n\n");
			sb.append(">>> Team eCart.");
			
			msg.setText(sb.toString());

			// part 2 : send the mail message
			Transport t = session.getTransport();
			t.connect(username, password);
			t.sendMessage(msg, msg.getAllRecipients());
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
	}

}










