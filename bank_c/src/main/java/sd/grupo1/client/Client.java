package sd.grupo1.client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.security.Policy;
import java.util.List;

import sd.grupo1.client.dto.BankAccountDTO;
import sd.grupo1.client.service.InterfaceSD;
import sd.grupo1.client.serviceImp.InterfaceSDImp;
import sd.grupo1.server.service.BankInterface;

public class Client extends Policy {

    private Client() {
    }

    public static void main(String[] args) throws RemoteException, NotBoundException {

    




    }
}