package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * Class used to connect a Java application to a MySql database.
 * The class contains methods for creating a connection, getting an active connection and 
 * closing a connection, a Statement or a ResultSet.
 * 
 * @author Lidia
 *
 */
public class ConnectionWarehouse {

	
	/**
	 * This class contains the name of the driver, the database location (DBURL), and the user and the password for accessing the MySQL Server
	 */
	private static final Logger LOGGER = Logger.getLogger(ConnectionWarehouse.class.getName());
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String DBURL = "jdbc:mysql://localhost:3306/warehouse?useSSL=false";
	private static final String USER = "root";
	private static final String PASS = "root";

	private static ConnectionWarehouse singleInstance = new ConnectionWarehouse();

	/**
	 * The connection to the DB will be placed in a Singleton* object
	 */
	private ConnectionWarehouse() {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private Connection createConnection() {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(DBURL, USER, PASS);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "An error occured while trying to connect to the database");
			e.printStackTrace();
		}
		return connection;
	}

	public static Connection getConnection() {
		return singleInstance.createConnection();
	}

	public static void close(Connection connection) {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				LOGGER.log(Level.WARNING, "An error occured while trying to close the connection");
			}
		}
	}

	public static void close(Statement statement) {
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				LOGGER.log(Level.WARNING, "An error occured while trying to close the statement");
			}
		}
	}

	public static void close(ResultSet resultSet) {
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				LOGGER.log(Level.WARNING, "An error occured while trying to close the ResultSet");
			}
		}
	}
}
