package training.web;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import training.dao.ProductDao;
import training.entity.LineItem;

@WebServlet("/add-to-cart")
public class AddToCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private ProductDao dao;

	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			String id = request.getParameter("id");
			String price = request.getParameter("price");

			HttpSession session = request.getSession();
			Map<String, LineItem> cart = (Map<String, LineItem>) session.getAttribute("cart");

			if (cart == null) {
				cart = new LinkedHashMap<>();
				session.setAttribute("cart", cart);
			}

			if (cart.containsKey(id)) {
				LineItem item = cart.get(id);
				item.setQuantity(item.getQuantity() + 1);
			} else {
				LineItem item = new LineItem();
				// item.getProduct().setId(new Integer(id));
				item.setProduct(dao.getProduct(new Integer(id)));
				item.setQuantity(1);
				item.setUnitPrice(new Double(price));
				cart.put(id, item);
			}
			response.sendRedirect("./");

		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

}
