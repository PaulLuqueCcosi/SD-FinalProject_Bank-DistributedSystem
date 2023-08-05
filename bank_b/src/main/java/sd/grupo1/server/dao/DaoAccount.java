package sd.grupo1.server.dao;


import java.util.List;

import sd.grupo1.server.entities.Account;
import sd.grupo1.server.entities.User;

/**
 * Interfaz que define los métodos de acceso a datos para la entidad Account.
 */
public interface DaoAccount {

    public List<Account> userAccounts(User u);
    
    public List<Account> getAll();
    /**
     * Obtiene una cuenta por su número de cuenta.
     * @param accNum El número de cuenta a buscar.
     * @return La cuenta encontrada o null si no existe.
     */
    Account getAccountByAccNum(int accNum);

    /**
     * Guarda una cuenta en la base de datos.
     * @param account La cuenta a guardar.
     */
    void saveAccount(Account account);

    /**
     * Actualiza una cuenta en la base de datos.
     * @param account La cuenta a actualizar.
     */
    void updateAccount(Account account);

    /**
     * Elimina una cuenta de la base de datos.
     * @param account La cuenta a eliminar.
     */
    void deleteAccount(Account account);
}