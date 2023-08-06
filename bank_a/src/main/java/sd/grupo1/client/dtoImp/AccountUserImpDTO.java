package sd.grupo1.client.dtoImp;

import sd.grupo1.client.dto.AccountUserDTOCliente;
import sd.grupo1.server.entities.Account;
import sd.grupo1.server.entities.User;

/**
 * Implementación de la interfaz AccountUserDTO que representa un objeto de
 * transferencia de datos (DTO)
 * para un usuario de cuenta. Esta clase proporciona la implementación vacía de
 * los métodos definidos
 * en la interfaz AccountUserDTO. Los métodos no tienen una funcionalidad real y
 * lanzarán una excepción
 * UnsupportedOperationException si se llaman, ya que deben ser implementados
 * por las clases que extiendan esta clase.
 */
public class AccountUserImpDTO implements AccountUserDTOCliente {

    int numberAccount;
    double balanceAccount;
    String nameUser;
    String lastnameUser;

    /**
     * Se debe de crear un objeto segun los 2 parametros que se le pasa
     * 
     * @param accoount
     * @param user
     */
    public AccountUserImpDTO(Account accoount, User user) {
        this.numberAccount = accoount.getAcc_num();
        this.balanceAccount = accoount.getAcc_bal();
        this.nameUser = user.getNombre();
        this.lastnameUser = user.getApellido();

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getNumberAccount() {

        return this.numberAccount;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getBalanceAccount() {

        return this.balanceAccount;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getNameUserAccount() {
        return this.nameUser;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getLastNameUserAccount() {
        return this.lastnameUser;
    }

}
