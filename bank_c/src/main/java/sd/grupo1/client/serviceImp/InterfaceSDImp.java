package sd.grupo1.client.serviceImp;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;

import sd.grupo1.client.dto.BankAccountDTO;
import sd.grupo1.client.dtoImp.BankAccountImpDTO;
import sd.grupo1.client.service.InterfaceSD;
import sd.grupo1.server.dto.AccountUserDTO;
import sd.grupo1.server.service.BankInterface;

/**
 * Implementación de la interfaz InterfaceSD que representa el servicio del
 * sistema distribuido (SD) para el cliente.
 * Esta clase proporciona la implementación de los métodos para interactuar con
 * el sistema bancario distribuido.
 */
public class InterfaceSDImp implements InterfaceSD {


    private BankInterface getBankInterface(String bankName, int port) throws RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry("127.0.0.1", port);
        return (BankInterface) registry.lookup(bankName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<BankAccountDTO> getAllAcount(int DNI) throws RemoteException {
        List<BankAccountDTO> bankAccounts = new ArrayList<>();
        try {
            BankInterface bankA = getBankInterface("BankA", 1099);
            BankInterface bankB = getBankInterface("BankB", 2099);
            BankInterface bankC = getBankInterface("BankC", 3099);

            // You can now call the listAccount() method on each bank interface to get the account information.
            List<AccountUserDTO> accountsA = bankA.listAccount(DNI);
            List<AccountUserDTO> accountsB = bankB.listAccount(DNI);
            List<AccountUserDTO> accountsC = bankC.listAccount(DNI);

            BankAccountDTO bankA = new BankAccountImpDTO(bankA, accountsA);

            

    
        } catch (NotBoundException e) {
            e.printStackTrace();
        }
        return bankAccounts;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int deposit(BankInterface bank, int acnt, int amt) throws RemoteException {
        throw new UnsupportedOperationException("Método 'deposit' no implementado");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int withdraw(BankInterface bank, int acnt, int amt) throws RemoteException {
        throw new UnsupportedOperationException("Método 'withdraw' no implementado");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean transfer(BankInterface bankOri, int acntOri, BankInterface bankDes, int acntDes, int amt)
            throws RemoteException {
        throw new UnsupportedOperationException("Método 'transfer' no implementado");
    }

}
