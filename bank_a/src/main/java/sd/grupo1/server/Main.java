package sd.grupo1.server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.security.*;

import sd.grupo1.server.dao.Dao;
import sd.grupo1.server.daoImp.jsonImp.JsonFileUserDAO;
import sd.grupo1.server.entities.Account;
import sd.grupo1.server.entities.User;
import sd.grupo1.server.serviceImp.BankInterfaceImp;

/**
 * Hello world!
 *
 */
public class Main extends Policy {
    int portBankService = 1099;
    String name = "BankA";
    String location = "Arequipa";
    String directory = "BD_A/";
    String bdFileUser = "bd_a_user.json";
    String bdFileAccount = "bd_a_account.json";
    String textReady = "Bank A in redy \nname : " + name + "\nin port : " + portBankService + "\nlocation : "
            + location;

    private void startServer() {
        try {


            Dao daoUser = new JsonFileUserDAO(directory, bdFileUser);


            Registry registry = LocateRegistry.createRegistry(portBankService);
            registry.rebind(name, new BankInterfaceImp(daoUser, name, location));

            System.out.println(textReady);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws RemoteException {

        Main main = new Main();
        System.setProperty("java.rmi.server.hostname", "0.0.0.0");// sets the RMI
        main.startServer();
       
    }

}
