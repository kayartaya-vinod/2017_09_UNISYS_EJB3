package training.web;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import training.dao.DaoException;
import training.dao.ProductDao;

//@WebFilter("/*")
public class DefaultFilter implements Filter {

	@EJB
	ProductDao dao;

	public void init(FilterConfig fConfig) throws ServletException {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		try {
			request.setAttribute("brands", dao.getAllBrands());
			request.setAttribute("categories", dao.getAllCategories());
		} catch (DaoException e) {
			e.printStackTrace();
		}

		chain.doFilter(request, response);
	}

	public void destroy() {
	}

}
