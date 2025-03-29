package Interface;
import java.rmi.Remote;
import java.rmi.RemoteException;
public interface authInterfaceRMI extends Remote{
	boolean login(String email, String password) throws RemoteException;
    boolean register(String name, String email, String password, int number ) throws RemoteException;
}
