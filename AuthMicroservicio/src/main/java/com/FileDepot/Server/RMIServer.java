package com.FileDepot.Server;
import com.FileDepot.Interfaces.AuthInterface;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIServer {
	 public static void main(String[] args) {
	        try {
	            // Crear el servicio
	            AuthInterface authService = new AuthServiceImpl();
	            
	            // Registrar el servicio en el puerto 
	            Registry registry = LocateRegistry.createRegistry(4423);
	            registry.rebind("AuthService", authService);
	            
	            System.out.println("Servidor RMI activo. Servicio 'AuthService' registrado.");
	        } catch (Exception e) {
	            System.err.println("Error en el servidor RMI: " + e.getMessage());
	            e.printStackTrace();
	        }
	    }
}
