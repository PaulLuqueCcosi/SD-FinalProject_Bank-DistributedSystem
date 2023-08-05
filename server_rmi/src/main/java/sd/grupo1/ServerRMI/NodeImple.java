package sd.grupo1.ServerRMI;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class NodeImple extends UnicastRemoteObject implements NodeInterface {
    private List<String[]> referenciasRemotas;

    protected NodeImple() throws RemoteException {
        super();
        referenciasRemotas = new ArrayList<String[]>();
        // TODO Auto-generated constructor stub
    }


    @Override
    public void registrarBanco(String bancoNombre, String url) throws RemoteException {
        referenciasRemotas.add(new String[] {bancoNombre, url});
        System.out.println("Bank:"+bancoNombre+" registrado");
        throw new UnsupportedOperationException("Unimplemented method 'registrarBanco'");
    }

    // @Override
    // public List<String[]> obtenerReferenciasRemotas() throws RemoteException {
    //     return referenciasRemotas;
    // }

}
