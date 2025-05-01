package com.FileDepot.connectionBD;

import java.sql.*;

import org.mindrot.jbcrypt.BCrypt;
public class DatabaseOperations {

	public int registerUser(String name ,String email, String password, long number) {
		String passwordHash = BCrypt.hashpw(password, BCrypt.gensalt());

		String sql = "INSERT INTO usuario (name, email, password_hash, phone, created_at, is_active) VALUES (?, ?, ?, ?, NOW(), 1)";

		try (Connection conn = DatabaseConnection.getConnection();
			 PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

			stmt.setString(1, name);
			stmt.setString(2, email);
			stmt.setString(3, passwordHash);
			stmt.setLong(4, number);

			int rows = stmt.executeUpdate();

			if (rows > 0) {
				ResultSet rs = stmt.getGeneratedKeys();
				if (rs.next()) {
					int userId = rs.getInt(1);
					System.out.println("Usuario registrado con ID: " + userId);
					return userId;
				}
			}

		} catch (SQLException e) {
			System.err.println("Error en registerUser: " + e.getMessage());
		}

		return -1;
	}

	public int loginUser(String email, String password) {
		String sql = "SELECT idUsuario, password_hash FROM usuario WHERE email = ?";

		try (Connection conn = DatabaseConnection.getConnection();
			 PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setString(1, email);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				int userId = rs.getInt("idUsuario");
				String passwordHashGuardado = rs.getString("password_hash");

				System.out.println("Hash recuperado de BD: '" + passwordHashGuardado + "'");
				System.out.println("Longitud del hash: " + passwordHashGuardado.length());

				if (passwordHashGuardado == null || !passwordHashGuardado.startsWith("$2a$")) {
					System.err.println("Error: Hash no válido en BD para el usuario " + email);
					return -1;
				}

				if (BCrypt.checkpw(password, passwordHashGuardado)) {
					return userId;
				} else {
					return -1;
				}
			}
			return -1;
		} catch (SQLException e) {
			System.err.println("Error en loginUser: " + e.getMessage());
			return -1;
		}
	}

	public int getUserByEmail(String email) {
		String sql = "SELECT idUSUARIO FROM usuario WHERE email = ?";

		try (Connection conn = DatabaseConnection.getConnection();
			 PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setString(1, email);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
                return rs.getInt("idUSUARIO");
			}

		} catch (SQLException e) {
			System.err.println("Error en getUserByEmail: " + e.getMessage());
		}

		return -1;
	}

}
