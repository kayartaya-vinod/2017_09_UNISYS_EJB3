package web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.resource.ResourceException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sample.adapter.HelloWorldConnection;
import com.sample.adapter.HelloWorldConnectionFactory;

@WebServlet(urlPatterns = "/TestRA", loadOnStartup = 1)
public class HelloServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Resource(mappedName = "java:/eis/HelloWorld")
	private HelloWorldConnectionFactory connectionFactory;

	@PostConstruct
	public void test() {
		System.out.println("connectionFactory = " + connectionFactory);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HelloWorldConnection connection = null;
		String result = null;
		try {
			connection = connectionFactory.getConnection();
			result = connection.helloWorld(); // interaction

		} catch (ResourceException e) {
			e.printStackTrace();
		}

		PrintWriter out = response.getWriter();
		out.println(result);

		out.flush();
	}
}
