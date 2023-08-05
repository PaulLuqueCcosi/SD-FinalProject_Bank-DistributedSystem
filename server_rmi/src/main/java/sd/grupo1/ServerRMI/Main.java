package sd.grupo1.ServerRMI;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Main {
    public static void main(String[] args) {

        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        try {
            // NodeImple nodoCentral = new NodeImple();
            // LocateRegistry.createRegistry(1099); // Puerto RMI
            // Naming.rebind("DNS_RMI", nodoCentral);
            // System.out.println("Nodo central en ejecución.");


            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("dns", new NodeImple());
            System.out.println("system is ready");


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
