package sd.grupo1.ServerRMI;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import sd.grupo1.service.BankInterface;

public class NodeImple extends UnicastRemoteObject implements NodeInterface {

    protected NodeImple() throws RemoteException {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
    public List<BankInterface> getAllBankInterfaces() throws RemoteException {

        String nameBankA = "BankA";
        String ipBankA = "127.0.0.1";
        int portBankA = 1099;

        String nameBankB = "BankB";
        String ipBankB = "127.0.0.1";
        int portBankB = 2099;

        String nameBankC = "BankC";
        int portBankC = 3099;
        String ipBankC = "127.0.0.1";

        Registry bank_reg;
        BankInterface bank;

        List<BankInterface> banks = new ArrayList<BankInterface>();

        // banco A

        try {
            bank_reg = LocateRegistry.getRegistry(ipBankA, portBankA);
            bank = (BankInterface) bank_reg.lookup(nameBankA);

            if(bank != null){
                banks.add(bank);
            }
        } catch (RemoteException | NotBoundException e) {
            System.out.println("Error al conectar al banco de " + nameBankB);
            e.printStackTrace();
        }

          // banco B

        try {
            bank_reg = LocateRegistry.getRegistry(ipBankB, portBankB);
            bank = (BankInterface) bank_reg.lookup(nameBankA);

            if(bank != null){
                banks.add(bank);
            }
        } catch (RemoteException | NotBoundException e) {
            System.out.println("Error al conectar al banco de " + nameBankB);
        }

          // banco C

        try {
            bank_reg = LocateRegistry.getRegistry(ipBankC, portBankC);
            bank = (BankInterface) bank_reg.lookup(nameBankC);

            if(bank != null){
                banks.add(bank);
            }
        } catch (RemoteException | NotBoundException e) {
            System.out.println("Error al conectar al banco de " + nameBankB);
        }


        return banks;
 
    }

}
