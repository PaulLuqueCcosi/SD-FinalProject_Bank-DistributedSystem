package sd.grupo1.server.dto;

import java.io.Serializable;

public interface AccountUserDTO extends Serializable{
    int getNumberAccount();

    int getBalanceAccount();

    String getNameUserAccount();

    String getLastNameUserAccount();
}
