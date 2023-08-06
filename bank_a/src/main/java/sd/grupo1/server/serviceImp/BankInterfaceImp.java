package sd.grupo1.server.serviceImp;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import sd.grupo1.exception.NoAccountException;
import sd.grupo1.exception.NoUserException;
import sd.grupo1.server.dao.Dao;
import sd.grupo1.server.dto.AccountUserDTO;
import sd.grupo1.server.dtoImp.AccountUserImpDTO;
import sd.grupo1.server.entities.Account;
import sd.grupo1.server.entities.User;
import sd.grupo1.server.service.BankInterface;

/**
 * Implementación de la interfaz remota BankInterface que representa el servicio
 * del banco.
 * Esta clase extiende UnicastRemoteObject, lo que permite que los objetos de
 * esta clase sean
 * utilizados para la comunicación remota mediante RMI.
 */
public class BankInterfaceImp extends UnicastRemoteObject implements BankInterface, Serializable {

    // private DaoUser daoUser = new JsonFileUserDAO("dataBaseUser_A.json");
    // private DaoAccount daoAccount = new
    // JsonFileAccountDAO("dataBaseAccount_A.json");

    private Dao dao;
    // private DaoAccount daoAccount;
    private String name;
    private String location;

    /**
     * Constructor por defecto que llama al constructor de la clase base
     * UnicastRemoteObject.
     * El constructor de UnicastRemoteObject debe manejar RemoteException.
     *
     * @throws RemoteException Si ocurre un error al instanciar el objeto remoto.
     */
    // public BankInterfaceImp() throws RemoteException {
    // super();
    // }

    public BankInterfaceImp(Dao dao, String name, String location)
            throws RemoteException {
        super();
        this.dao = dao;
        this.name = name;
        this.location = location;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean checkUserExist(String DNI) throws RemoteException {

        User user = dao.getUserByDni(DNI);
        return (user != null);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<AccountUserDTO> listAccount(String DNI) throws RemoteException, NoUserException {
        User user = dao.getUserByDni(DNI);
        if (user == null) {
            throw new NoUserException("El usuario con DNI: " + DNI + " no existe");
        }

        List<AccountUserDTO> accountUserDTOs = new ArrayList<>();
        List<Account> userAccounts = dao.userAccounts(user);
        for (Account account : userAccounts) {
            AccountUserDTO accountUserDTO = new AccountUserImpDTO(account, user);
            accountUserDTOs.add(accountUserDTO);
        }
        return accountUserDTOs;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean checkAccPin(int acnt, int pin) throws RemoteException, NoAccountException {

        Account account = dao.getAccountByAccNum(acnt);
        if (account == null) {
            throw new NoAccountException("La cuenta con número: " + acnt + " no existe");
        }

        return (account.getAcc_pin() == pin);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean deposit(int acnt, double amt) throws RemoteException, NoAccountException {
        Account account = dao.getAccountByAccNum(acnt);

        if (account == null) {
            throw new NoAccountException("La cuenta con número: " + acnt + " no existe");
        }
        account.setAcc_bal(account.getAcc_bal() + amt);
        dao.updateAccount(account);
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean withdraw(int acnt, double amt) throws RemoteException, NoAccountException {
        Account account = dao.getAccountByAccNum(acnt);
        if (account == null) {
            throw new NoAccountException("La cuenta con número: " + acnt + " no existe");
        }
        account.setAcc_bal(account.getAcc_bal() - amt);
        dao.updateAccount(account);
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double checkBalance(int acnt) throws RemoteException, NoAccountException {
        Account account = dao.getAccountByAccNum(acnt);
        if (account == null) {
            throw new NoAccountException("La cuenta con número: " + acnt + " no existe");
        }

        return account.getAcc_bal();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isPossibleWithdraw(int acnt, double amt) throws RemoteException, NoAccountException {
        Account account = dao.getAccountByAccNum(acnt);
        if (account == null) {
            throw new NoAccountException("La cuenta con número: " + acnt + " no existe");
        }
        return (account.getAcc_bal() >= amt);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean transfer(int acntOrigin, int acnDesty, double amt) throws RemoteException, NoAccountException {
        Account originAccount = dao.getAccountByAccNum(acntOrigin);
        Account destAccount = dao.getAccountByAccNum(acnDesty);

        if (originAccount == null) {
            throw new NoAccountException("La cuenta origen con número: " + acntOrigin + " no existe");
        }

        if (destAccount == null) {
            throw new NoAccountException("La cuenta destino con número: " + acnDesty + " no existe");
        }

        if (originAccount.getAcc_bal() >= amt) {
            originAccount.setAcc_bal(originAccount.getAcc_bal() - amt);
            dao.updateAccount(originAccount);
            destAccount.setAcc_bal(destAccount.getAcc_bal() + amt);
            dao.updateAccount(destAccount);
            return true;
        }
        return false;
    }

    @Override
    public String getName() throws RemoteException {
        return name;
    }

    @Override
    public String getLocation() throws RemoteException {
        return location;
    }

}
