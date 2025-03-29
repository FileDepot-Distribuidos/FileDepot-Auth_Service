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
    public boolean login(String email, String password) throws RemoteException {
        return operations.loginUser(email, password);
    }

    @Override
    public boolean register(String name, String email, String password, int number) throws RemoteException {
        return operations.registerUser(name, email, password, number);
    }
}