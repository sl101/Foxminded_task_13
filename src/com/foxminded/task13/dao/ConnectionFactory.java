package com.foxminded.task13.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.apache.log4j.Logger;

public class ConnectionFactory {

	public static String url = null;
	public static String login = null;
	public static String password = null;
	public static String driver = null;
	private static Properties property;
	private static final Logger log = Logger.getLogger(ConnectionFactory.class);

	private static void getProperties() throws ScheduleException {
		FileInputStream fis;
		property = new Properties();
		try {
			fis = new FileInputStream("C:/workspace_Fox/Task13/src/config.properties");
			property.load(fis);
			driver = property.getProperty("db.driver");
			url = property.getProperty("db.host");
			login = property.getProperty("db.login");
			password = property.getProperty("db.password");
		} catch (IOException e) {
			log.error("Problem to get properties", e);
			throw new ScheduleException("Problem to get properties", e);
		}
	}

	private static Connection createConnection() throws ScheduleException {
		Connection connection = null;
		try {
			Class.forName(driver);
			connection = DriverManager.getConnection(url, login, password);
		} catch (SQLException | ClassNotFoundException e) {
			log.error("Problem to create connection", e);
			throw new ScheduleException("Problem to create connection", e);
		}
		return connection;
	}

	public static Connection getConnection() throws ScheduleException {
		getProperties();
		return createConnection();
	}

	public static void closeConnection(Connection connection, Statement statement, ResultSet resultSet)
			throws ScheduleException {
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				log.error("Problem to close resultSet", e);
				throw new ScheduleException("Problem to close resultSet", e);
			}
		}
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				log.error("Problem to close statement", e);
				throw new ScheduleException("Problem to close statement", e);
			}
		}
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				log.error("Problem to close connection", e);
				throw new ScheduleException("Problem to close connection", e);
			}
		}
	}

	public static void closeConnection(Connection connection, Statement statement) throws ScheduleException {
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				log.error("Problem to close statement", e);
				throw new ScheduleException("Problem to close statement", e);
			}
		}
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				log.error("Problem to close connection", e);
				throw new ScheduleException("Problem to close connection", e);
			}
		}
	}
}
