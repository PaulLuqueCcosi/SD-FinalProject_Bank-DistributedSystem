package sd.grupo1.client.dto;

import java.util.List;

import sd.grupo1.server.dto.AccountUserDTO;
import sd.grupo1.server.service.BankInterface;

/**
 * Esta interfaz representa un Objeto de Transferencia de Datos (DTO) para una cuenta bancaria.
 * Proporciona métodos para obtener información sobre la entidad bancaria y las cuentas asociadas.
 */
public interface BankAccountDTO {

    public String getName();

    public String getLocation();

    /**
     * Obtiene la interfaz remota de la entidad bancaria.
     *
     * @return La interfaz remota de la entidad bancaria (BankInterface).
     */
    public BankInterface getBank();

    /**
     * Obtiene una lista de objetos AccountUserDTO que representan las cuentas asociadas a la entidad bancaria.
     *
     * @return Una lista de objetos AccountUserDTO que representan las cuentas asociadas a la entidad bancaria.
     */
    public List<AccountUserDTO> getAccounts();

}
