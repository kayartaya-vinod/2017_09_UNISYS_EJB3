package client;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;

import ejb.UserService;

public class Main {

	public static void main(String[] args) throws Exception {
		String jndiName = "ejb:/EJB_Types_Demo/UserServiceBean!ejb.UserService";
		Properties props = new Properties();
		props.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
		Context ctx = new InitialContext(props);
		
		UserService service1 = (UserService) ctx.lookup(jndiName);
		UserService service2 = (UserService) ctx.lookup(jndiName);
		UserService service3 = (UserService) ctx.lookup(jndiName);
		System.out.println("service1==service2 is " + (service1==service2));
		
		service1.setUsername("Vinod");
		service2.setUsername("John Doe");
		service3.setUsername("Jane Doe");
		
		System.out.println("(Service 1) Username from the ejb is " + service1.getUsername());
		System.out.println("(Service 2) Username from the ejb is " + service2.getUsername());
		System.out.println("(Service 3) Username from the ejb is " + service3.getUsername());
		
		ctx.close();
	}
}




