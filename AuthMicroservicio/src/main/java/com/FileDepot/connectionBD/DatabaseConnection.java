package com.FileDepot.connectionBD;

import com.FileDepot.util.ConfigLoader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	private static final String HOST = ConfigLoader.get("DB_HOST");
	private static final String PORT = ConfigLoader.get("DB_PORT");
	private static final String NAME = ConfigLoader.get("DB_NAME");
	private static final String USER = ConfigLoader.get("DB_USER");
	private static final String PASSWORD = ConfigLoader.get("DB_PASSWORD");

	private static final String URL = "jdbc:mariadb://" + HOST + ":" + PORT + "/" + NAME;

	public static Connection getConnection() throws SQLException {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			return DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (ClassNotFoundException e) {
			throw new SQLException("Driver de MariaDB no encontrado", e);
		}
	}
}
