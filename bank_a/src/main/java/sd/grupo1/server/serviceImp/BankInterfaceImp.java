package sd.grupo1.server.serviceImp;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import sd.grupo1.server.dto.AccountUserDTO;
import sd.grupo1.server.service.BankInterface;

/**
 * Implementación de la interfaz remota BankInterface que representa el servicio del banco.
 * Esta clase extiende UnicastRemoteObject, lo que permite que los objetos de esta clase sean
 * utilizados para la comunicación remota mediante RMI.
 */
public class BankInterfaceImp extends UnicastRemoteObject implements BankInterface, Serializable {

    /**
     * Constructor por defecto que llama al constructor de la clase base UnicastRemoteObject.
     * El constructor de UnicastRemoteObject debe manejar RemoteException.
     *
     * @throws RemoteException Si ocurre un error al instanciar el objeto remoto.
     */
    public BankInterfaceImp() throws RemoteException {
        super();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean checkUserExist(int DNI) throws RemoteException {
        
        throw new UnsupportedOperationException("Método 'checkUserExist' no implementado");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<AccountUserDTO> listAccount(int DNI) throws RemoteException {
        throw new UnsupportedOperationException("Método 'listAccount' no implementado");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean checkAccPin(int acnt, int pin) throws RemoteException {
        throw new UnsupportedOperationException("Método 'checkAccPin' no implementado");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean deposit(int acnt, double amt) throws RemoteException {
        throw new UnsupportedOperationException("Método 'deposit' no implementado");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean withdraw(int acnt, double amt) throws RemoteException {
        throw new UnsupportedOperationException("Método 'withdraw' no implementado");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double checkBalance(int acnt) throws RemoteException {
        throw new UnsupportedOperationException("Método 'checkBalance' no implementado");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isPossibleWithdraw(int acnt, double amt) throws RemoteException {
        throw new UnsupportedOperationException("Método 'isPossibleWithdraw' no implementado");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean transfer(int acntOrigin, int acnDesty, double amt) throws RemoteException {
        throw new UnsupportedOperationException("Método 'transfer' no implementado");
    }

}
