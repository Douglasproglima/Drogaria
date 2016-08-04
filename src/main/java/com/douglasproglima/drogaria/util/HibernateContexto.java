package com.douglasproglima.drogaria.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class HibernateContexto implements ServletContextListener {
	@Override
	public void contextDestroyed(ServletContextEvent eventoParar) {
		HibernateUtil.getSessionFactory().close();
	}

	@Override
	public void contextInitialized(ServletContextEvent eventoInicio) {
		HibernateUtil.getSessionFactory();
	}
}