package training.web;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import training.dao.CustomerDao;
import training.entity.Customer;
import training.utils.PasswordUtil;

@WebServlet("/login-register")
public class LoginRegisterServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/pages/login-register.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameter("action").equals("login")) {
			doLogin(request, response);
		} else if (request.getParameter("action").equals("register")) {
			doRegister(request, response);
		}
	}

	@EJB
	CustomerDao dao;

	protected void doLogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			password = PasswordUtil.hashPassword(password, "md5");

			Customer customer = dao.getCustomer(email, password);
			System.out.println("customer is " + customer);

			if (customer != null) {
				// need to store the customer in session
				request.getSession().setAttribute("customer", customer);

				String redirectUrl = (String) request.getSession().getAttribute("URL_TO_REDIRECT");
				if (redirectUrl == null) {
					redirectUrl = "./";
				} else {
					request.getSession().removeAttribute("URL_TO_REDIRECT");
				}
				// clientside rediection
				response.sendRedirect(redirectUrl);
				return;
			} else {
				request.setAttribute("loginError", "Invalid email/password");
				// serverside redirection
				request.getRequestDispatcher("/WEB-INF/pages/login-register.jsp").forward(request, response);
			}
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

	protected void doRegister(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}
}
