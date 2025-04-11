package com.FileDepot.CLientePrueba;


import com.FileDepot.Interfaces.AuthInterface;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIClient {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 4423);
            AuthInterface authService = (AuthInterface) registry.lookup("AuthService");
            
            // Prueba de registro y login
            int isRegistered = authService.register(
                "mm", "no@example.com", "ola como vas", 343424321
            );
            boolean isLoggedIn = authService.login("no@example.com", "ola como vas");
            
            System.out.println("Registro exitoso: " + isRegistered);
            System.out.println("Login exitoso: " + isLoggedIn);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
