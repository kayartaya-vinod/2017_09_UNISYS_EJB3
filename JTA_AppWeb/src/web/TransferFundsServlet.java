package web;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.AccountService;

@WebServlet("/TransferFundsServlet")
public class TransferFundsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	AccountService service;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			request.setAttribute("accounts", service.getAllAccounts());
			request.setAttribute("records", service.getAllTransactionRecords());

			request.getRequestDispatcher("/WEB-INF/pages/transfer.jsp").forward(request, response);
		} catch (Exception e) {
			throw new ServletException(e);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			String fromAcNum = request.getParameter("from_account_number");
			String toAcNum = request.getParameter("to_account_number");
			String amount = request.getParameter("amount");

			service.transferFunds(new Integer(toAcNum), new Integer(fromAcNum), new Double(amount));

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("err", e.getMessage());
		}
		this.doGet(request, response);
	}

}
