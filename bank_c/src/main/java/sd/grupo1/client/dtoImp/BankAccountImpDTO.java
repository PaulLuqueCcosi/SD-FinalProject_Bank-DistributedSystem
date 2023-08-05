package sd.grupo1.client.dtoImp;

import java.util.List;

import org.stringtemplate.v4.compiler.CodeGenerator.region_return;

import sd.grupo1.client.dto.BankAccountDTO;
import sd.grupo1.server.dto.AccountUserDTO;
import sd.grupo1.server.service.BankInterface;

/**
 * BankAccountImpDTO
 */
public class BankAccountImpDTO implements BankAccountDTO{

    private BankInterface bank;
    List<AccountUserDTO> lista;

    public BankAccountImpDTO(BankAccountDTO bankA, List<AccountUserDTO> listAccUser ){
        this.bank = (BankInterface) bankA;
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

    
}