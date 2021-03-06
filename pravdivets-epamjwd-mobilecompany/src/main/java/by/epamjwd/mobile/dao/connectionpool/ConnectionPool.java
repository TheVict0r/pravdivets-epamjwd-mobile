package by.epamjwd.mobile.dao.connectionpool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Locale;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamjwd.mobile.dao.connectionpool.exception.ConnectionPoolException;



public final class ConnectionPool {

	private static final int DEFAULT_POOL_SIZE = 5;
	private BlockingQueue<Connection> connectionQueue;
	private BlockingQueue<Connection> givenAwayConQueue;
	
	private final static Logger LOGGER = LogManager.getLogger(ConnectionPool.class);

	
	String driverName;
	String url;
	String user;
	String password;
	int poolSize;

	private ConnectionPool() {

	}

	private static class PoolHolder{
		static final ConnectionPool INSTANCE = new ConnectionPool();
	}
	
	public static ConnectionPool getInstance() {
		return PoolHolder.INSTANCE;
	}

	
	public void initPoolData(String databaseName) throws ConnectionPoolException {
		
		DBResourceManager dbResourseManager = DBResourceManager.getInstance();
		
		dbResourseManager.initBundle(databaseName);
		
		driverName = dbResourseManager.getValue(DBParameter.DB_DRIVER);
		url = dbResourseManager.getValue(DBParameter.DB_URL);
		user = dbResourseManager.getValue(DBParameter.DB_USER);
		password = dbResourseManager.getValue(DBParameter.DB_PASSWORD);
		
		try {
			poolSize = Integer.parseInt(dbResourseManager.getValue(DBParameter.DB_POLL_SIZE));
		} catch (NumberFormatException e) {
			poolSize = DEFAULT_POOL_SIZE;
		}

		Locale.setDefault(Locale.ENGLISH);
		
		try {
			Class.forName(driverName);
			givenAwayConQueue = new ArrayBlockingQueue<Connection>(poolSize);
			connectionQueue = new ArrayBlockingQueue<Connection>(poolSize);
			for (int i = 0; i < poolSize; i++) {
				Connection connection = DriverManager.getConnection(url, user, password);
				connectionQueue.add(connection);
			}
		} catch (SQLException e) {
			LOGGER.error("SQLException in ConnectionPool", e);
			throw new ConnectionPoolException("SQLException in ConnectionPool", e);
		} catch (ClassNotFoundException e) {
			LOGGER.error("Can't find database driver class", e);
			throw new ConnectionPoolException("Can't find database driver class", e);
			}
	}
		

	public Connection takeConnection() throws ConnectionPoolException {
		Connection connection = null;
		try {
			connection = connectionQueue.take();
			givenAwayConQueue.add(connection);
		} catch (InterruptedException e) {
			LOGGER.error("Error connecting to the data source", e);
			throw new ConnectionPoolException("Error connecting to the data source", e);
		}
		return connection;
	}

    public void releaseConnection(Connection connection) throws ConnectionPoolException  {
        if (connection != null) {
        	givenAwayConQueue.remove(connection);
            try {
                connectionQueue.put(connection);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                LOGGER.error("Unable to release connection", e);
                throw new ConnectionPoolException("Unable to release connection", e);
            }
        }
    }
	
	public void dispose() throws ConnectionPoolException {
		try {
			closeConnectionsQueue(givenAwayConQueue);
			closeConnectionsQueue(connectionQueue);
		} catch (SQLException e) {
			LOGGER.error("Error closing the connection", e);
			throw new ConnectionPoolException("Error closing the connection", e);
		}
	}

	private void closeConnectionsQueue(BlockingQueue<Connection> queue) throws SQLException {
		Connection connection;
		while ((connection = queue.poll()) != null) {
			if (!connection.getAutoCommit()) {
				connection.commit();
			}
			connection.close();
		}
	}

}


