package sd.grupo1.client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import sd.grupo1.server.service.BankInterface;



public class Client {

    private Client() {
    }

    public static void main(String[] args) throws RemoteException, MalformedURLException, NotBoundException {

    
        BankInterface bankA = (BankInterface) Naming.lookup("rmi://localhost:5000/bank");



    }
}