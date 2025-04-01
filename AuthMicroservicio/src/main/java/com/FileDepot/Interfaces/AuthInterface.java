package com.FileDepot.Interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface AuthInterface extends Remote {
    boolean login(String email, String password) throws RemoteException;
    boolean register(String name, String email, String password, long number) throws RemoteException;

}
