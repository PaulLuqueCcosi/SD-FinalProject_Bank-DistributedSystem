package sd.grupo1.server.dao;

import java.util.List;

import sd.grupo1.server.entities.Account;
import sd.grupo1.server.entities.User;

public interface Dao {

    // ACCOUNTS

    Account createAccount(User user);

    public List<Account> userAccounts(User u);

    public List<Account> getAllAccounts();

    /**
     * Obtiene una cuenta por su número de cuenta.
     * 
     * @param accNum El número de cuenta a buscar.
     * @return La cuenta encontrada o null si no existe.
     */
    Account getAccountByAccNum(int accNum);

    /**
     * Guarda una cuenta en la base de datos.
     * 
     * @param account La cuenta a guardar.
     */
    void saveAccount(Account account);

    /**
     * Actualiza una cuenta en la base de datos.
     * 
     * @param account La cuenta a actualizar.
     */
    void updateAccount(Account account);

    /**
     * Elimina una cuenta de la base de datos.
     * 
     * @param account La cuenta a eliminar.
     */
    void deleteAccount(Account account);

    // USERS
    public List<User> getAllUsers();

    List<Account> accountsUser(User user);

    public User createUser(String Name, String lastName, String DNI);

    /**
     * Obtiene un usuario por su ID.
     * 
     * @param id El ID del usuario a buscar.
     * @return El usuario encontrado o null si no existe.
     */
    User getUserById(int id);

    /**
     * Obtiene un usuario por su número de DNI.
     * 
     * @param dni El número de DNI del usuario a buscar.
     * @return El usuario encontrado o null si no existe.
     */
    User getUserByDni(String dni);

    /**
     * Guarda un usuario en la base de datos.
     * 
     * @param user El usuario a guardar.
     */
    void saveUser(User user);

    /**
     * Actualiza un usuario en la base de datos.
     * 
     * @param user El usuario a actualizar.
     */
    void updateUser(User user);

    /**
     * Elimina un usuario de la base de datos.
     * 
     * @param user El usuario a eliminar.
     */
    void deleteUser(User user);
}
