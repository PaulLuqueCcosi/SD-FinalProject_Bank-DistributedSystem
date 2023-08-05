package sd.grupo1.server.serviceImp;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import sd.grupo1.server.dao.DaoAccount;
import sd.grupo1.server.dao.DaoUser;
import sd.grupo1.server.daoImp.JsonFileAccountDAO;
import sd.grupo1.server.daoImp.JsonFileUserDAO;
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

    private DaoUser daoUser = new JsonFileUserDAO("dataBaseUser.json");
    private DaoAccount daoAccount = new JsonFileAccountDAO("dataBaseAccount.json");

    /**
     * Constructor por defecto que llama al constructor de la clase base
     * UnicastRemoteObject.
     * El constructor de UnicastRemoteObject debe manejar RemoteException.
     *
     * @throws RemoteException Si ocurre un error al instanciar el objeto remoto.
     */
    public BankInterfaceImp() throws RemoteException {
        super();
    }

    public BankInterfaceImp(DaoUser daoUser , DaoAccount daoAccount) throws RemoteException {
        this();
        this.daoAccount = daoAccount;
        this.daoUser = daoUser;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean checkUserExist(int DNI) throws RemoteException {

        User user = daoUser.getUserByDni(String.valueOf(DNI));
        return (user != null);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<AccountUserDTO> listAccount(int DNI) throws RemoteException {
        User user = daoUser.getUserByDni(String.valueOf(DNI));
        if (user == null) {
            return null;
        }

        List<AccountUserDTO> accountUserDTOs = new ArrayList<>();
        List<Account> userAccounts = daoAccount.userAccounts(user);
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
    public boolean checkAccPin(int acnt, int pin) throws RemoteException {

        Account account = daoAccount.getAccountByAccNum(acnt);
        return (account != null && account.getAcc_pin() == pin);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean deposit(int acnt, double amt) throws RemoteException {
        Account account = daoAccount.getAccountByAccNum(acnt);
        if (account != null) {
            account.setAcc_bal(account.getAcc_bal() + amt);
            daoAccount.updateAccount(account);
            return true;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean withdraw(int acnt, double amt) throws RemoteException {
        Account account = daoAccount.getAccountByAccNum(acnt);
        if (account != null && account.getAcc_bal() >= amt) {
            account.setAcc_bal(account.getAcc_bal() - amt);
            daoAccount.updateAccount(account);
            return true;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double checkBalance(int acnt) throws RemoteException {
        Account account = daoAccount.getAccountByAccNum(acnt);
        if (account != null) {
            return account.getAcc_bal();
        }
        return -1; // Indica que la cuenta no existe.
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isPossibleWithdraw(int acnt, double amt) throws RemoteException {
        Account account = daoAccount.getAccountByAccNum(acnt);
        return (account != null && account.getAcc_bal() >= amt);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean transfer(int acntOrigin, int acnDesty, double amt) throws RemoteException {
        Account originAccount = daoAccount.getAccountByAccNum(acntOrigin);
        Account destAccount = daoAccount.getAccountByAccNum(acnDesty);
        if (originAccount != null && destAccount != null && originAccount.getAcc_bal() >= amt) {
            originAccount.setAcc_bal(originAccount.getAcc_bal() - amt);
            destAccount.setAcc_bal(destAccount.getAcc_bal() + amt);
            daoAccount.updateAccount(originAccount);
            daoAccount.updateAccount(destAccount);
            return true;
        }
        return false;
    }

}
