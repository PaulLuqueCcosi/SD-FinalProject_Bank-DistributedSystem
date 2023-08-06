package sd.grupo1.client.dtoImp;

import java.rmi.RemoteException;
import java.util.List;


import sd.grupo1.client.dto.BankAccountDTO;
import sd.grupo1.server.dto.AccountUserDTO;
import sd.grupo1.server.service.BankInterface;

/**
 * BankAccountImpDTO
 */
public class BankAccountImpDTO implements BankAccountDTO{

    private BankInterface bank;
    List<AccountUserDTO> lista;

    public BankAccountImpDTO(BankInterface bank, List<AccountUserDTO> listAccUser ){
        this.bank = bank;
        this.lista = listAccUser;

    }

    @Override
    public BankInterface getBank() {
        return bank;
    }

    @Override
    public List<AccountUserDTO> getAccounts() {
        return lista;
    }

    @Override
    public String getName() {
        try {
            return bank.getName();
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getLocation() {
        try {
            return bank.getLocation();
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    
}