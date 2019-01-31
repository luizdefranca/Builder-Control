package ca.programmerlife.framework.hibernate.session;

import java.io.PrintWriter;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

import javax.faces.bean.ApplicationScoped;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.engine.SessionFactoryImplementor;

import ca.programmerlife.framework.dao.ConnectionUtil;

/**
 * Responsible for establishing a connection with Hibernate
 * @author luiz
 *
 */
@ApplicationScoped
public class HibernateUtil implements Serializable{
 
	private static final long serialVersionUID = 6868828213882986671L;
	public static String JAVA_COMP_ENV_JDBC_DATA_SOURCE = "java:/comp/env/jdbc/datasource";
	
	private static SessionFactory sessionFactory = buildSessionFactory();

	/**
	 * Responsible for reading the hibernate.cfg.xml configuration file.
	 * @return SessionFactory
	 */
	private static SessionFactory buildSessionFactory() {
		
		try {
			if(sessionFactory == null) {
				sessionFactory = new Configuration().configure().buildSessionFactory();
				
			}
			return sessionFactory;
		}
		catch (Exception e){
			e.printStackTrace();
			throw new ExceptionInInitializerError("Error creating connection SessionFactory");
			
		}
		
	}
	
	/**
	 * Return the current SessionFactory
	 * @return SessionFactory
	 */
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	/**
	 * Return the current Session
	 * @return Session
	 */
	public static Session getCurrentSession() {
		return getSessionFactory().getCurrentSession();
	}
	
	/**
	 * Open a new session within SessionFactory
	 * @return Session
	 */
	public static Session openSession() {
		if(sessionFactory == null) {
			buildSessionFactory();
		}
		return sessionFactory.openSession();
	}

	/**
	 * Obtain a Connection from configured connection provider
	 * @return Connection SQL
	 * @throws SQLException
	 */
	public static Connection getConnectionProvider() throws SQLException{
		return ((SessionFactoryImplementor) sessionFactory).getConnectionProvider().getConnection();
	}
	
	/**
	 * Obtain a Connection from InitialContext
	 * @return Connection from InitialContext java:comp/env/jdbc/datasource
	 * @throws Exception
	 */
	public static Connection getConnection() throws Exception{
		InitialContext context = new InitialContext();
		DataSource dataSource = (DataSource) context.lookup(JAVA_COMP_ENV_JDBC_DATA_SOURCE);
		return dataSource.getConnection();
		
	}
	
	/**
	 * Obtain a datasource from JNDI Tomcat
	 * @return DataSource JNDI Tomcat
	 * @throws NamingException
	 */
	public DataSource getDataSourceJndi() throws NamingException{
		InitialContext context = new InitialContext();
		 return (DataSource) context.lookup(ConnectionUtil.JAVA_COMP_ENV_JDBC_DATA_SOURCE);
	}
	
}
