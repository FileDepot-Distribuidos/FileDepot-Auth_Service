package com.FileDepot.connectionBD;

import java.sql.*;

import org.mindrot.jbcrypt.BCrypt;
public class DatabaseOperations {

	public boolean registerUser(String name ,String email, String password, long number) {
		String passwordHash = BCrypt.hashpw(password, BCrypt.gensalt());

		String sql = "INSERT INTO usuario (name, email, password_hash, phone, created_at, is_active) VALUES (?, ?, ?, ?, NOW(), 1)";

		try(Connection conn = DatabaseConnection.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setString(1, name);
			stmt.setString(2, email);
			stmt.setString(3, passwordHash);
			stmt.setLong(4, number); // Usa setLong aquí
			stmt.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
			stmt.setBoolean(6, true);

			return stmt.executeUpdate() > 0;

		} catch(SQLException e) {
			System.err.println("Error en registerUser: " + e.getMessage());
			return false;
		}
	}

	public boolean loginUser(String email, String password) {
	    String sql = "SELECT password_hash FROM usuario WHERE email = ?";
	    
	    try (Connection conn = DatabaseConnection.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
	        
	        stmt.setString(1, email);
	        ResultSet rs = stmt.executeQuery();
	        
	        if (rs.next()) {
	            String passwordHashGuardado = rs.getString("password_hash");
	            
	            // Depuración: Imprime el hash para verificar
	            System.out.println("Hash recuperado de BD: '" + passwordHashGuardado + "'");
	            System.out.println("Longitud del hash: " + passwordHashGuardado.length());
	            
	            // Verifica que el hash sea válido
	            if (passwordHashGuardado == null || !passwordHashGuardado.startsWith("$2a$")) {
	                System.err.println("Error: Hash no válido en BD para el usuario " + email);
	                return false;
	            }
	            
	            // Compara la contraseña
	            return BCrypt.checkpw(password, passwordHashGuardado);
	        }
	        return false; // Usuario no existe
	        
	    } catch (SQLException e) {
	        System.err.println("Error en loginUser: " + e.getMessage());
	        return false;
	    }
	}
}
