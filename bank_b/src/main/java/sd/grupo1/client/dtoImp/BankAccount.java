package sd.grupo1.client.dtoImp;

import java.rmi.RemoteException;

import sd.grupo1.server.dto.AccountUserDTO;
import sd.grupo1.server.service.BankInterface;

public class BankAccount {

    private BankInterface nameBank;
    private AccountUserDTO acc;

    public BankAccount(BankInterface bank, AccountUserDTO account) {
        this.nameBank = bank;
        this.acc = account;

    }

    public BankInterface getBank() {
        return nameBank;
    }
    public String getNameBank() {
        try {
            return nameBank.getName();
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
        }
        return null;
    }


    public AccountUserDTO getAcc() {
        return acc;
    }


}
