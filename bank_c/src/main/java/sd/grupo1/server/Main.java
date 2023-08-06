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
    int portBankService = 3099;
    String name = "BankC";
    String location = "Lima";
    String directory = "BD_C/";
    String bdFileUser = "bd_c_user.json";
    String bdFileAccount = "bd_c_account.json";
    String textReady = "Bank c in redy \nname : " + name + "\nin port : " + portBankService + "\nlocation : "
            + location;

    private void startServer() {
        try {

            // creacion de los DAO
            // DaoAccount daoAccount = new JsonFileAccountDAO(directory, bdFileAccount);
            Dao daoUser = new JsonFileUserDAO(directory, bdFileUser);

            // creacion de la interfaz
            // BankInterface bankInterface = new BankInterfaceImp(daoUser, daoAccount);

            Registry registry = LocateRegistry.createRegistry(portBankService);
            registry.rebind(name, new BankInterfaceImp(daoUser, name, location));

            System.out.println(textReady);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws RemoteException {

        Main main = new Main();
        System.setProperty("java.rmi.server.hostname", "127.0.0.1");// sets the RMI
        main.startServer();

        // String directory = "BD_A/";
        // String bdFileUser = "bd_a_user.json";
        // // String bdFileAccount = "bd_a_account.json";
        // // creacion de los DAO
        // Dao daoUser = new JsonFileUserDAO(directory, bdFileUser);
        // // Dao daoAccount = new JsonFileAccountDAO(directory, bdFileAccount);

        // User user = daoUser.getUserById(0);
        // Account a = daoUser.getAccountByAccNum(0);
        // user.addAccount(a);
        // daoUser.updateUser(user);
        // daoAccount.updateAccount(a);

        // for (User u2 : daoUser.getAllUsers()) {
        //     System.out.println(u2);
        // }
        // System.out.println("---------");

        // for (Account ac : daoUser.getAllAccounts()) {
        //     System.out.println(ac);
        // }

        // System.out.println("depositoooooooooooo");
        // a.setAcc_bal(100);
        // daoUser.updateAccount(a);
        // Account a2 = daoUser.getAccountByAccNum(0);

        // a2.setAcc_bal(90);
        // daoUser.updateAccount(a2);

        // for (User u2 : daoUser.getAllUsers()) {
        //     System.out.println(u2);
        // }
        // System.out.println("---------");

        // for (Account ac : daoUser.getAllAccounts()) {
        //     System.out.println(ac);
        // }
    }

}
