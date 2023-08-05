package sd.grupo1.client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.security.Policy;

import sd.grupo1.server.service.BankInterface;

public class Client extends Policy {

    private Client() {
    }

    public static void main(String[] args) throws RemoteException, NotBoundException {

        Registry bank_reg = LocateRegistry.getRegistry("127.0.0.1",1099);
        BankInterface bank = (BankInterface) bank_reg.lookup("Bank");


        Registry bank_regC = LocateRegistry.getRegistry("127.0.0.1",3099);
        BankInterface bankC = (BankInterface) bank_regC.lookup("BankC");


        if(bank.checkUserExist(73057755)) {
            System.out.println("TRUE A");
        }

        if(bankC.checkUserExist(74057755)){
            System.out.println("TRUE C");
        }


    }
}