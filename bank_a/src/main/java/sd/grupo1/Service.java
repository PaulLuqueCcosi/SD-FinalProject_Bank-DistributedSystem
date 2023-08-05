package sd.grupo1;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Service extends Remote {
    
    public boolean checkAccNum(int acnt) throws RemoteException;
    public String listUsers() throws RemoteException;
}
