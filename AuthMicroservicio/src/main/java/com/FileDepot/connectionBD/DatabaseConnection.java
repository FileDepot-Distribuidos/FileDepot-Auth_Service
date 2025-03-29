package com.FileDepot.connectionBD;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	 private static final String URL = "jdbc:mariadb://localhost:3306/user";
	    private static final String USER = "root";
	    private static final String PASSWORD = "Arango22";

	    public static Connection getConnection() throws SQLException {
	        try {
	            Class.forName("org.mariadb.jdbc.Driver");
	            return DriverManager.getConnection(URL, USER, PASSWORD);
	        } catch (ClassNotFoundException e) {
	            throw new SQLException("Driver de MariaDB no encontrado", e);
	        }
	    }
}