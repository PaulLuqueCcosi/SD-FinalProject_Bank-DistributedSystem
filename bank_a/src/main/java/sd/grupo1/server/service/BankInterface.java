package sd.grupo1.server.service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import sd.grupo1.server.dto.AccountUserDTO;

public interface BankInterface extends Remote {

    public boolean checkUserExist(int DNI) throws RemoteException;

    public List<AccountUserDTO> listAccount(int DNI) throws RemoteException;
    
    public boolean checkAccPin(int acnt, int pin) throws RemoteException;

    public void deposit(int acnt, int amt) throws RemoteException;
    
    public void withdraw(int acnt, int amt) throws RemoteException;
    
    public int checkBalance(int acnt) throws RemoteException;

    public boolean isPossibleWithdraw(int acnt, int amt) throws RemoteException;

    public boolean transfer(int acntOrigin, int acnDesty, int amt) throws RemoteException;

}