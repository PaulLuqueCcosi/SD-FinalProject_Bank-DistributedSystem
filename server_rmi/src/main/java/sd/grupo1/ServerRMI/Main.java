package sd.grupo1.ServerRMI;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.security.Policy;

public class Main extends Policy {
    private void startServer() {
        try {

            Registry registry = LocateRegistry.createRegistry(5000);
            registry.rebind("allBanks", new NodeImple());

            System.out.println("ServidroCentralListo");
            System.out.println("Puerto 5000");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws RemoteException {

        Main main = new Main();
        System.setProperty("java.rmi.server.hostname", "0.0.0.0");// sets the RMI
        System.setProperty("java.rmi.server.hostname", "0.0.0.0");// sets the RMI
        main.startServer();
        System.setSecurityManager(new SecurityManager());

    }
}
