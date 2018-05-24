package com.unisys.tests;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;

import training.dao.JpaTester;

public class TestEjb {

	public static void main(String[] args) throws Exception {

		// ear_name/ejb_module_name/bean_name!full_qualified_remote_interface_name
		String jndiName = "ejb:ECartApp/ECartAppEJB/JpaTesterImpl!training.dao.JpaTester";

		// a variable to represent a JNDI service
		Context ctx;

		// by default, this refers to a local JNDI (JNDI in the current JVM)
		// alternately, this can read from a properties file called
		// "jndi.properties"

		// When using JBOSS/Wildfly, we have a file called
		// jboss-ejb-client.properties
		Properties props = new Properties();
		props.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");

		ctx = new InitialContext(props);

		JpaTester tester = (JpaTester) ctx.lookup(jndiName);

		System.out.println("Got an instanceof type " + tester.getClass() + " for variable dao");

		tester.testJpaFeature();

		System.out.println("Check the output on the server console");

		ctx.close();

	}
}
