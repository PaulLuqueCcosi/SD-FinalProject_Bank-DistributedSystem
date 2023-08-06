package sd.grupo1.server.service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import sd.grupo1.exception.NoAccountException;
import sd.grupo1.exception.NoUserException;
import sd.grupo1.server.dto.AccountUserDTO;

/**
 * Esta interfaz representa la interfaz remota para el servicio del banco.
 * Define los métodos que pueden ser invocados de forma remota por los clientes.
 * Todos los métodos declaran que pueden arrojar una excepción RemoteException,
 * que es una excepción específica de RMI que indica un error en la comunicación
 * remota.
 */
public interface BankInterface extends Remote {

    public String getName() throws RemoteException;

    public String getLocation() throws RemoteException;

    /**
     * 
     * /**
     * Verifica si un usuario existe en el banco basándose en su número de DNI
     * (Documento Nacional de Identidad).
     *
     * @param DNI El número de DNI del usuario a verificar.
     * @return true si el usuario existe en el banco, false en caso contrario.
     * @throws RemoteException Si ocurre un error en la comunicación remota.
     */
    public boolean checkUserExist(String DNI) throws RemoteException;

    /**
     * Obtiene una lista de cuentas asociadas a un usuario basándose en su número de
     * DNI (Documento Nacional de Identidad).
     *
     * @param DNI El número de DNI del usuario para el cual se desea obtener las
     *            cuentas.
     * @return Una lista de objetos AccountUserDTO que representan las cuentas
     *         asociadas al usuario.
     * @throws RemoteException Si ocurre un error en la comunicación remota.
     */
    public List<AccountUserDTO> listAccount(String DNI) throws RemoteException, NoUserException;

    /**
     * Verifica si el PIN ingresado corresponde a la cuenta dada.
     *
     * @param acnt El número de cuenta para el cual se desea verificar el PIN.
     * @param pin  El PIN a verificar.
     * @return true si el PIN ingresado es correcto, false en caso contrario.
     * @throws RemoteException Si ocurre un error en la comunicación remota.
     */
    public boolean checkAccPin(int acnt, int pin) throws RemoteException, NoAccountException;

    /**
     * Realiza un depósito en una cuenta especificada.
     *
     * @param acnt El número de cuenta en el que se desea realizar el depósito.
     * @param amt  La cantidad de dinero a depositar.
     * @return true el deposito fue correcto, false en caso contrario.
     * @throws RemoteException Si ocurre un error en la comunicación remota.
     */
    public boolean deposit(int acnt, double amt) throws RemoteException, NoAccountException;

    /**
     * Realiza un retiro de una cuenta especificada.
     *
     * @param acnt El número de cuenta en el que se desea realizar el retiro.
     * @param amt  La cantidad de dinero a retirar.
     * @return true el retino fue correcto, false en caso contrario.
     * @throws RemoteException Si ocurre un error en la comunicación remota.
     */
    public boolean withdraw(int acnt, double amt) throws RemoteException, NoAccountException;

    /**
     * Obtiene el saldo actual de una cuenta especificada.
     *
     * @param acnt El número de cuenta para el cual se desea obtener el saldo.
     * @return El saldo actual de la cuenta.
     * @throws RemoteException Si ocurre un error en la comunicación remota.
     */
    public double checkBalance(int acnt) throws RemoteException, NoAccountException;

    /**
     * Verifica si es posible realizar un retiro de la cantidad especificada de una
     * cuenta dada,
     * teniendo en cuenta el saldo disponible y posibles límites de sobregiro.
     *
     * @param acnt El número de cuenta para el cual se desea verificar el retiro.
     * @param amt  La cantidad de dinero a retirar.
     * @return true si es posible realizar el retiro, false en caso contrario.
     * @throws RemoteException Si ocurre un error en la comunicación remota.
     */
    public boolean isPossibleWithdraw(int acnt, double amt) throws RemoteException, NoAccountException;

    /**
     * Realiza una transferencia de fondos desde una cuenta de origen a una cuenta
     * de destino.
     *
     * @param acntOrigin El número de cuenta de origen desde donde se transferirán
     *                   los fondos.
     * @param acnDesty   El número de cuenta de destino donde se recibirán los
     *                   fondos.
     * @param amt        La cantidad de dinero a transferir.
     * @return true si la transferencia se realiza con éxito, false en caso
     *         contrario.
     * @throws RemoteException Si ocurre un error en la comunicación remota.
     */
    public boolean transfer(int acntOrigin, int acnDesty, double amt) throws RemoteException, NoAccountException;
}
