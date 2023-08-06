package sd.grupo1.client.dto;

import java.io.Serializable;

import sd.grupo1.server.dto.AccountUserDTO;

/**
 * Esta interfaz representa un Objeto de Transferencia de Datos (DTO) para un
 * usuario de cuenta.
 * Proporciona métodos para obtener información sobre los detalles de un usuario
 * de cuenta.
 * Esta interfaz extiende la interfaz Serializable para permitir una fácil
 * serialización y deserialización
 * de objetos al transferir datos entre el cliente y el servidor.
 */
public interface AccountUserDTOCliente extends AccountUserDTO{

    // /**
    //  * Obtiene el número de cuenta del usuario.
    //  *
    //  * @return El número de cuenta.
    //  */
    // int getNumberAccount();

    // /**
    //  * Obtiene el saldo de la cuenta del usuario.
    //  *
    //  * @return El saldo de la cuenta.
    //  */
    // int getBalanceAccount();

    // /**
    //  * Obtiene el nombre del usuario asociado con la cuenta.
    //  *
    //  * @return El nombre del usuario.
    //  */
    // String getNameUserAccount();

    // /**
    //  * Obtiene el apellido del usuario asociado con la cuenta.
    //  *
    //  * @return El apellido del usuario.
    //  */
    // String getLastNameUserAccount();
}
