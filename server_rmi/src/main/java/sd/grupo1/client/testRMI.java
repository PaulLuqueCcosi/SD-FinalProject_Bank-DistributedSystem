package sd.grupo1.client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;

import sd.grupo1.ServerRMI.NodeInterface;
import sd.grupo1.service.BankInterface;

public class testRMI {
    public static void main(String[] args) {


        Registry bank_reg;
        NodeInterface node;

        List<BankInterface> banks = new ArrayList<BankInterface>();

        // banco A

        try {
            bank_reg = LocateRegistry.getRegistry("127.0.0.1", 5000);
            node = (NodeInterface) bank_reg.lookup("allBanks");

            System.out.println(node.getAllBankInterfaces().size());;
            // for (BankInterface bankInterface : banks) {
                
            // }
            System.out.println("gaaaaaaaaa");
        } catch (RemoteException | NotBoundException e) {
            System.out.println("Error al conectar al banco de");
        }
    }
}
