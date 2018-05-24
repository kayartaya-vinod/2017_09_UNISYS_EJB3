package training.web;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import training.dao.ProductDao;

@WebServlet("/ProductCountServlet")
public class ProductCountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private ProductDao dao;

	public ProductCountServlet() {
		System.out.println(">>> ProductCountServlet instantiated, and dao is ..." + dao);
	}
	
	@PostConstruct
	public void setup(){
		System.out.println(">>> ProductCountServlet initialized, and dao is ..." + dao);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setAttribute("pc", dao.getProductCount());
		request.getRequestDispatcher("/count.jsp").forward(request, response);

	}

}
