package sd.grupo1.ServerRMI;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface NodeInterface extends Remote , Serializable{
    public void registrarBanco(String bancoNombre, String url) throws RemoteException;
    // public List<String[]> obtenerReferenciasRemotas() throws RemoteException;

}
