package sd.grupo1.server.service;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.List;

import sd.grupo1.server.dto.AccountUserDTO;

public class BankImp implements BankInterface, Serializable{




    @Override
    public boolean checkUserExist(int DNI) throws RemoteException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'checkUserExist'");
    }

    @Override
    public List<AccountUserDTO> listAccount(int DNI) throws RemoteException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listAccount'");
    }

    @Override
    public boolean checkAccPin(int acnt, int pin) throws RemoteException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'checkAccPin'");
    }

    @Override
    public void deposit(int acnt, int amt) throws RemoteException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deposit'");
    }

    @Override
    public void withdraw(int acnt, int amt) throws RemoteException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'withdraw'");
    }

    @Override
    public int checkBalance(int acnt) throws RemoteException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'checkBalance'");
    }

    @Override
    public boolean isPossibleWithdraw(int acnt, int amt) throws RemoteException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isPossibleWithdraw'");
    }

    @Override
    public boolean transfer(int acntOrigin, int acnDesty, int amt) throws RemoteException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'transfer'");
    }

    



}