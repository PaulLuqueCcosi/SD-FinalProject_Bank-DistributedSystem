package sd.grupo1.server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.security.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

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
    String textReady = "Bank A in redy";

    private void startServer() {
        try {

            // LocateRegistry.createRegistry(5000);
            // BankInterface stub = new BankImp();
            // Naming.rebind("rmi://localhost:5000/bank", stub);

            // // Registry registry = LocateRegistry.createRegistry(portBankService);
            // // registry.rebind("Bank", new ServiceImplement());
            // System.out.println("system bank A server RMI is ready");

            // System.out.println(stub.toString());

            Registry registry = LocateRegistry.createRegistry(portBankService);
            registry.rebind(name, new BankInterfaceImp());

            System.out.println(textReady);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws RemoteException {

        // Main main = new Main();
        // System.setProperty("java.rmi.server.hostname", "127.0.0.1");// sets the RMI
        // service to start on local host
        // main.startServer();

        // Configuración de Hibernate
        Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
        SessionFactory sessionFactory = configuration.buildSessionFactory();

        // Crear algunas instancias de Account y User
        Account account1 = new Account();
        account1.setAcc_num(1);
        account1.setAcc_pin(1234);
        account1.setAcc_bal(1000.0);
        account1.setAcc_status(1);

        Account account2 = new Account();
        account2.setAcc_num(2);
        account2.setAcc_pin(5678);
        account2.setAcc_bal(500.0);
        account2.setAcc_status(1);

        User user = new User();
        user.setId(1);
        user.setNombre("John");
        user.setApellido("Doe");
        user.setDni("12345678");
        user.getAccounts().add(account1);
        user.getAccounts().add(account2);

        // Guardar los objetos en la base de datos
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.save(account1);
        session.save(account2);
        session.save(user);

        session.getTransaction().commit();
        session.close();
    }

}
