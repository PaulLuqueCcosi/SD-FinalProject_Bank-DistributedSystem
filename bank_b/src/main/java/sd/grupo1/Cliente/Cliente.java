package sd.grupo1.Cliente;

import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import sd.grupo1.Service;

public class Cliente {

    private Cliente() {}

    public static void main(String[] args) {



        try {
            String host = "192.168.1.36";
            int port = 2099;
            Registry registry = LocateRegistry.getRegistry(port);
            
            Remote stub =  registry.lookup("Bank");
            // String response = stub.listUsers();
            // System.out.println("response: " + response);
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}