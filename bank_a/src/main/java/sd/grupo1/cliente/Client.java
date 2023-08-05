package sd.grupo1.cliente;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import sd.grupo1.Service;

public class Client {

    private Client() {}

    public static void main(String[] args) {

        try {
            String host = "localhost";
            int port = 3099;
            Registry registry = LocateRegistry.getRegistry(host, port);
            Service stub = (Service) registry.lookup("BankB");
            String response = stub.listUsers();
            System.out.println("response: " + response);
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}