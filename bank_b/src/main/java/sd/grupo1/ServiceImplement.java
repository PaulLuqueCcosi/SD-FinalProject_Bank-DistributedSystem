package sd.grupo1;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ServiceImplement extends UnicastRemoteObject implements Service {

    protected ServiceImplement() throws RemoteException {
        super();
        //TODO Auto-generated constructor stub
    }

    @Override
    public boolean checkAccNum(int acnt) throws RemoteException {
        // TODO Auto-generated method stub
        System.out.println("Si existe gaaaaaaaaaaaaaaa");
        System.out.println(acnt);
        return true;
    }

    @Override
    public String listUsers() throws RemoteException {
        // TODO Auto-generated method stub
        System.out.println("Aquiiiiiiiiii lista de los usuarios");
        return "Lista de usuarios del Bnco B ; luis, lsdf, sdf,sd f,sd f";
    }
    
}
