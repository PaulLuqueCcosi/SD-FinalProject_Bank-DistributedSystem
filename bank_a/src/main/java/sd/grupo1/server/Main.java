package sd.grupo1.server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.security.*;

import sd.grupo1.server.serviceImp.BankInterfaceImp;

/**
 * Hello world!
 *
 */
public class Main extends Policy{
    int portBankService = 1099;
    String name = "BankA";
    String textReady = "Bank A in redy";

    private void startServer() {
        try {

            // LocateRegistry.createRegistry(5000);
            // BankInterface stub = new BankImp();
            // Naming.rebind("rmi://localhost:5000/bank", stub);
                

            // // Registry registry = LocateRegistry.createRegistry(portBankService);
            // // registry.rebind("Bank", new ServiceImplement());
            // System.out.println("system bank A server RMI  is ready");

            // System.out.println(stub.toString());

            Registry registry = LocateRegistry.createRegistry(portBankService);
            registry.rebind(name, new BankInterfaceImp());
            
            System.out.println(textReady);



        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws RemoteException {


        Main main = new Main();
        System.setProperty("java.rmi.server.hostname", "127.0.0.1");// sets the RMI service to start on local host
        main.startServer();
    }
}