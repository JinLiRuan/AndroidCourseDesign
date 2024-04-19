package com.sise.util;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;



public class HibernateUtil {

	private static SessionFactory sf;
	private static Configuration configuration;
	
	static{
		configuration = new Configuration().configure();
		sf = configuration.buildSessionFactory();
	}
	
	
	public static Session getSession() {
		Session session = sf.getCurrentSession();
		return session;
	}
}
