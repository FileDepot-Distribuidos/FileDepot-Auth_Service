package Interface;
import java.rmi.Remote;
import java.rmi.RemoteException;
public interface authInterfaceRMI {
	boolean login(String email, String password) throws RemoteException;
    boolean register(String email, String password) throws RemoteException;
}
