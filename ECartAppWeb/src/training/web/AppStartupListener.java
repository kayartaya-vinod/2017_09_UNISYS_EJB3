package training.web;

import javax.ejb.EJB;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import training.dao.DaoException;
import training.dao.ProductDao;

@WebListener
public class AppStartupListener implements ServletContextListener {
	
	@EJB
	ProductDao dao;

	public void contextInitialized(ServletContextEvent evt) {
		System.out.println(">>>> Initializing the application and storing brands and categories in applicationScope...");
		ServletContext ctx = evt.getServletContext();
		try {
			ctx.setAttribute("brands", dao.getAllBrands());
			ctx.setAttribute("categories", dao.getAllCategories());
			
			// this will overridden on different requests
			ctx.setAttribute("products", dao.getAllProducts(1));
			ctx.setAttribute("caption", "All products");
			
		} catch (DaoException e) {
			e.printStackTrace();
		}
	}

	public void contextDestroyed(ServletContextEvent evt) {
	}

}
