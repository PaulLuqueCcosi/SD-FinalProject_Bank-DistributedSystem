package sd.grupo1.client.serviceImp;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;

import sd.grupo1.client.dto.BankAccountDTO;
import sd.grupo1.client.service.InterfaceSD;
import sd.grupo1.server.service.BankInterface;

/**
 * Implementación de la interfaz InterfaceSD que representa el servicio del sistema distribuido (SD) para el cliente.
 * Esta clase proporciona la implementación de los métodos para interactuar con el sistema bancario distribuido.
 */
public class InterfaceSDImp implements InterfaceSD {

    /**
     * {@inheritDoc}
     */
    @Override
    public List<BankAccountDTO> getAllAcount(int DNI) throws RemoteException {

        // TODO

        Registry bank_reg = LocateRegistry.getRegistry("127.0.0.1",1099);
        BankInterface bank;
        try {
            bank = (BankInterface) bank_reg.lookup("Bank");
            bank.listAccount(DNI);
        } catch (NotBoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        Registry bank_regC = LocateRegistry.getRegistry("127.0.0.1",3099);
        BankInterface bankC;
        try {
            bankC = (BankInterface) bank_regC.lookup("BankC");
        } catch (NotBoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }



        throw new UnsupportedOperationException("Método 'getAllAcount' no implementado");
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
