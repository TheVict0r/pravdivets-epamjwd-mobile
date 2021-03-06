package by.epamjwd.mobile.controller.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamjwd.mobile.dao.connectionpool.ConnectionPool;
import by.epamjwd.mobile.dao.connectionpool.exception.ConnectionPoolException;

public class AppContextListener implements ServletContextListener {

	private final static Logger LOGGER = LogManager.getLogger(AppContextListener.class);

	private final static String BASE_NAME = "db";

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ConnectionPool pool = ConnectionPool.getInstance();
		try {
			pool.initPoolData(BASE_NAME);
		} catch (ConnectionPoolException e) {
			LOGGER.error("Unable to initialize connection pool", e);
			throw new RuntimeException("Unable to initialize connection pool.", e);
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		ConnectionPool pool = null;
		pool = ConnectionPool.getInstance();
		try {
			pool.dispose();
		} catch (ConnectionPoolException e) {
			LOGGER.error("Unable to dispose connection pool", e);
			throw new RuntimeException("Unable to dispose connection pool.", e);
		}

	}
}
