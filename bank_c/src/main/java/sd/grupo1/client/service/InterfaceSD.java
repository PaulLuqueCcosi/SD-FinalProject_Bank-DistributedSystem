package sd.grupo1.client.service;

import java.rmi.RemoteException;
import java.util.List;

import sd.grupo1.client.dto.BankAccountDTO;
import sd.grupo1.exception.NoBankOnline;
import sd.grupo1.server.service.BankInterface;

/**
 * Esta interfaz representa la interfaz del servicio del sistema distribuido (SD) para el cliente.
 * Define los métodos que el cliente puede invocar para interactuar con el sistema bancario distribuido.
 */
public interface InterfaceSD {

    /**
     * Obtiene una lista de todas las cuentas asociadas a un usuario basándose en su número de DNI (Documento Nacional de Identidad).
     *
     * @param DNI El número de DNI del usuario para el cual se desea obtener las cuentas.
     * @return Una lista de objetos BankAccountDTO que representan las cuentas asociadas al usuario.
     * @throws RemoteException Si ocurre un error en la comunicación remota.
     */
    public List<BankAccountDTO> getAllAcount(String DNI);

    /**
     * Realiza un depósito en una cuenta especificada de una entidad bancaria.
     *
     * @param bank La interfaz remota de la entidad bancaria donde se realizará el depósito.
     * @param acnt El número de cuenta en el que se desea realizar el depósito.
     * @param amt La cantidad de dinero a depositar.
     * @return El nuevo saldo de la cuenta después del depósito.
     * @throws RemoteException Si ocurre un error en la comunicación remota.
     */
    public double deposit(BankInterface bank, int acnt, int amt) throws NoBankOnline;

    /**
     * Realiza un retiro de una cuenta especificada de una entidad bancaria.
     *
     * @param bank La interfaz remota de la entidad bancaria donde se realizará el retiro.
     * @param acnt El número de cuenta en el que se desea realizar el retiro.
     * @param amt La cantidad de dinero a retirar.
     * @return El nuevo saldo de la cuenta después del retiro.
     * @throws RemoteException Si ocurre un error en la comunicación remota.
     */
    public double withdraw(BankInterface bank, int acnt, int amt) throws NoBankOnline;

    /**
     * Realiza una transferencia de fondos desde una cuenta de una entidad bancaria de origen
     * a una cuenta de otra entidad bancaria de destino.
     *
     * @param bankOri La interfaz remota de la entidad bancaria de origen desde donde se transferirán los fondos.
     * @param acntOri El número de cuenta de origen desde donde se transferirán los fondos.
     * @param bankDes La interfaz remota de la entidad bancaria de destino donde se recibirán los fondos.
     * @param acntDes El número de cuenta de destino donde se recibirán los fondos.
     * @param amt La cantidad de dinero a transferir.
     * @return true si la transferencia se realiza con éxito, false en caso contrario.
     * @throws RemoteException Si ocurre un error en la comunicación remota.
     */
    public boolean transfer(BankInterface bankOri, int acntOri, BankInterface bankDes, int acntDes, double amt) throws NoBankOnline;

}
