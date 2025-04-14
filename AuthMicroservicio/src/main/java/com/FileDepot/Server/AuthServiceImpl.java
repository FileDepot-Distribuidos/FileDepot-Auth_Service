package com.FileDepot.Server;
import com.FileDepot.Interfaces.AuthInterface;
import com.FileDepot.connectionBD.DatabaseOperations;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class AuthServiceImpl extends UnicastRemoteObject implements AuthInterface {
    private final DatabaseOperations operations;

    public AuthServiceImpl() throws RemoteException {
        super(); 
        this.operations = new DatabaseOperations();
    }

    @Override
    public int login(String email, String password) throws RemoteException {
        return operations.loginUser(email, password);
    }

    @Override
    public int register(String name, String email, String password, long number) throws RemoteException {
        System.out.println("→ Llamada al método register desde RMI");
        System.out.println("  Nombre: " + name);
        System.out.println("  Email: " + email);
        System.out.println("  Teléfono: " + number);

        int result = operations.registerUser(name, email, password, number);
        System.out.println("  Resultado de registro: " + result);

        return result;
    }

}