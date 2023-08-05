package sd.grupo1;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Hello world!
 *
 */
public class App {
    int portBankService = 2099;
    private void startServer(){ 
        try {
            Registry registry = LocateRegistry.createRegistry(portBankService);
            registry.rebind("Bank", new ServiceImplement());
            System.out.println("system bank a is ready");
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }
     
    public static void main(String[] args) throws RemoteException {
        App main = new App();
        System.setProperty("java.rmi.server.hostname","127.0.0.1");// sets the RMI service to start on local host
        main.startServer();
    }
}