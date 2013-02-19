package hr.neos.mail.commons;

import java.util.Hashtable;
import java.util.Properties;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.naming.Context;
import javax.naming.InitialContext;

public class SimpleClient {

	/**
	 * Main method.
	 */
	public static void main(String[] args) {

		Context jndiContext = null;
		ConnectionFactory connectionFactory = null;
		Connection connection = null;
		Session session = null;
		Queue queue = null;
		MessageProducer messageProducer = null;

		try {
			Properties properties = new Properties();

			properties.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.enterprise.naming.SerialInitContextFactory");
			//properties.put(Context.PROVIDER_URL, "iiop://kickstartmail.neos.hr:7676"); // vm://localhost:
			properties.setProperty("java.naming.factory.url.pkgs", "com.sun.enterprise.naming");
			properties.setProperty("java.naming.factory.state", "com.sun.corba.ee.impl.presentation.rmi.JNDIStateFactoryImpl");
			properties.setProperty("org.omg.CORBA.ORBInitialHost", "192.168.137.70");  
			properties.setProperty("org.omg.CORBA.ORBInitialPort", "7676");

			jndiContext = new InitialContext(properties);

			System.out.println("TRAŽIM...");
			connectionFactory = (ConnectionFactory) jndiContext.lookup("jms/mailQueueFactory");
			System.out.println("NAŠAO!!!");

			// Lookup queue
			// get MessageProducer
			// Send TextMessage
		} catch (Exception ex) {
			System.out.println("Error: " + ex);
		}

		finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (JMSException e) {
					System.out.println("Error: " + e);
				}
			}
		}
	}
}