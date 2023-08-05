package sd.grupo1.server;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import sd.grupo1.server.service.BankImp;
import sd.grupo1.server.service.BankInterface;

/**
 * Hello world!
 *
 */
public class App {
    int portBankService = 2099;

    private void startServer() {
        try {

            LocateRegistry.createRegistry(5000);
            BankInterface stub = new BankImp();
            Naming.rebind("rmi://localhost:5000/bank", stub);
                

            // Registry registry = LocateRegistry.createRegistry(portBankService);
            // registry.rebind("Bank", new ServiceImplement());
            System.out.println("system bank A server RMI  is ready");

            System.out.println(stub.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws RemoteException {


        App main = new App();
        System.setProperty("java.rmi.server.hostname", "127.0.0.1");// sets the RMI service to start on local host
        main.startServer();
    }
}