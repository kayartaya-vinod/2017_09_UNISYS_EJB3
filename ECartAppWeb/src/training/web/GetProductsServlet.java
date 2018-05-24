package training.web;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import training.dao.ProductDao;
import training.entity.Product;

@WebServlet("/get-products")
public class GetProductsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	ProductDao dao;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String by = request.getParameter("by");
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		try {
			List<Product> products;
			switch(by){
			case "brand": 
				products = dao.getProductsByBrand(new Integer(id));
				request.setAttribute("caption", "Products by brand '" + name + "'");
				break;
			case "category":
				products = dao.getProductsByCategory(new Integer(id));
				request.setAttribute("caption", "Products by category '" + name + "'");
				break;
			default:
				products = dao.getAllProducts(1);
				request.setAttribute("caption", "All products");
			}
			request.setAttribute("products", products);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("/WEB-INF/pages/product-list.jsp")
			.forward(request, response);
	}

}







