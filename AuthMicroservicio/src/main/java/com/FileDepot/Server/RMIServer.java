package com.FileDepot.Server;

import com.FileDepot.Interfaces.AuthInterface;
import com.FileDepot.util.ConfigLoader;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.Remote;

public class RMIServer {
	public static void main(String[] args) {
		try {
			AuthInterface authService = new AuthServiceImpl();

			String host = ConfigLoader.get("RMI_HOST");
			int port = ConfigLoader.getInt("RMI_PORT");
			String serviceName = ConfigLoader.get("RMI_SERVICE_NAME");

			System.setProperty("java.rmi.server.hostname", host);

			Registry registry = LocateRegistry.createRegistry(port);
			registry.rebind(serviceName, authService);

			System.out.println("Servidor RMI activo");
			System.out.println("Host: " + host);
			System.out.println("Puerto: " + port);
			System.out.println("Servicio: " + serviceName);
		} catch (Exception e) {
			System.err.println("Error en el servidor RMI: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
