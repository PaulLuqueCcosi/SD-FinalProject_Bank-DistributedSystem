package sd.grupo1.server.dtoImp;

import sd.grupo1.server.dto.AccountUserDTO;
import sd.grupo1.server.entities.Account;
import sd.grupo1.server.entities.User;

/**
 * Implementación de la interfaz AccountUserDTO que representa un objeto de transferencia de datos (DTO)
 * para un usuario de cuenta. Esta clase proporciona la implementación vacía de los métodos definidos
 * en la interfaz AccountUserDTO. Los métodos no tienen una funcionalidad real y lanzarán una excepción
 * UnsupportedOperationException si se llaman, ya que deben ser implementados por las clases que extiendan esta clase.
 */
public class AccountUserImpDTO implements AccountUserDTO {

    int numberAccount;
    int balanceAccount;
    String nameUser;
    String lastnameUser;

    /**
     * Se debe de crear un objeto segun los 2 parametros que se le pasa
     * @param accoount
     * @param user
     */
    public AccountUserImpDTO(Account accoount, User user){
        
        // TODO
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getNumberAccount() {
        // TODO
        throw new UnsupportedOperationException("Método 'getNumberAccount' no implementado");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getBalanceAccount() {
        //TODO
        throw new UnsupportedOperationException("Método 'getBalanceAccount' no implementado");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getNameUserAccount() {
        // TODO
        throw new UnsupportedOperationException("Método 'getNameUserAccount' no implementado");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getLastNameUserAccount() {
        // TODO
        throw new UnsupportedOperationException("Método 'getLastNameUserAccount' no implementado");
    }

}
