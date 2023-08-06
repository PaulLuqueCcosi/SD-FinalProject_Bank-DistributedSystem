package sd.grupo1.ServerRMI;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import sd.grupo1.service.BankInterface;

public interface NodeInterface extends Remote , Serializable{
    public List<BankInterface> getAllBankInterfaces()  throws RemoteException ;


}
