package sd.grupo1.client.serviceImp;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;

import sd.grupo1.client.dto.BankAccountDTO;
import sd.grupo1.client.dtoImp.BankAccountImpDTO;
import sd.grupo1.client.service.InterfaceSD;
import sd.grupo1.exception.NoAccountException;
import sd.grupo1.exception.NoBankOnline;
import sd.grupo1.exception.NoUserException;
import sd.grupo1.server.dto.AccountUserDTO;
import sd.grupo1.server.service.BankInterface;

/**
 * Implementación de la interfaz InterfaceSD que representa el servicio del
 * sistema distribuido (SD) para el cliente.
 * Esta clase proporciona la implementación de los métodos para interactuar con
 * el sistema bancario distribuido.
 */
public class InterfaceSDImp implements InterfaceSD {

    private List<BankAccountDTO> addBankAccountDTOList(String nameBank, int port, String ip, String DNI,
            List<BankAccountDTO> list) throws NoBankOnline, NoUserException {

        Registry bank_reg;
        BankInterface bank;

        List<AccountUserDTO> accounts;

        try {
            bank_reg = LocateRegistry.getRegistry(ip, port);
        } catch (RemoteException e) {
            throw new NoBankOnline("Error al conectar al banco de " + nameBank);
        }

        try {
            bank = (BankInterface) bank_reg.lookup(nameBank);
            accounts = bank.listAccount(DNI);
        } catch (RemoteException | NotBoundException e) {
            throw new NoBankOnline("Error al conectar al banco de " + nameBank);
        }

        BankAccountImpDTO accountImp = new BankAccountImpDTO(bank, accounts);
        list.add(accountImp);

        return list;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<BankAccountDTO> getAllAcount(String DNI) {
        String nameBankA = "BankA";
        String ipBankA = "127.0.0.1";

        int portBankA = 1099;
        String nameBankB = "BankB";
        String ipBankB = "127.0.0.1";
        int portBankB = 2099;

        String nameBankC = "BankC";
        int portBankC = 3099;
        String ipBankC = "127.0.0.1";

        List<BankAccountDTO> bankAccountDTOs = new ArrayList<BankAccountDTO>();

        try {
            bankAccountDTOs = addBankAccountDTOList(nameBankA, portBankA, ipBankA, DNI, bankAccountDTOs);
        } catch (NoBankOnline e) {
            System.out.println(ipBankA + ": " + portBankA + "/" + nameBankA + " no se encuetra en linea");
        } catch (NoUserException e) {
            System.out.println("No ser DNI : " + DNI + " in bank " + nameBankA);
            // e.printStackTrace();
        }

        try {
            bankAccountDTOs = addBankAccountDTOList(nameBankB, portBankB, ipBankB, DNI, bankAccountDTOs);
        } catch (NoBankOnline e) {
            System.out.println(ipBankB + ": " + portBankB + "/" + nameBankB + " no se encuetra en linea");

        } catch (NoUserException e) {
            System.out.println("No ser DNI : " + DNI + " in bank " + nameBankB);
            // e.printStackTrace();
        }

        try {
            bankAccountDTOs = addBankAccountDTOList(nameBankC, portBankC, ipBankC, DNI, bankAccountDTOs);
        } catch (NoBankOnline e) {
            System.out.println(ipBankC + ": " + portBankC + "/" + nameBankC + " no se encuetra en linea");

        } catch (NoUserException e) {
            System.out.println("No ser DNI : " + DNI + " in bank " + nameBankC);
            // e.printStackTrace();
        }

        return bankAccountDTOs;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double deposit(BankInterface bank, int acnt, int amt) throws NoBankOnline {
        double newSaldo = -1;
        try {
            // Realizar el depósito usando el método remoto deposit de BankInterface
            boolean success = bank.deposit(acnt, amt);

            if (success) {
                System.out.println("Depósito realizado exitosamente en la cuenta " + acnt + ".");
                newSaldo = bank.checkBalance(acnt);
            } else {
                System.out.println("El depósito en la cuenta " + acnt + " ha fallado.");
            }
        } catch (RemoteException e) {
            System.out.println("Error de comunicación con el banco , no se realizaronlas acciones: ");
            throw new NoBankOnline("No se puede realizar el depósito en el banco remoto.");
        } catch (NoAccountException e) {
            System.out.println("No existe la cuenta " + acnt);
        }

        return newSaldo;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double withdraw(BankInterface bank, int acnt, int amt) throws NoBankOnline {
        double newSaldo = -1;

        try {

            // verificamos si se pude realizar el retiro
            boolean possibleWithdraw = bank.isPossibleWithdraw(acnt, amt);

            if (!possibleWithdraw) {
                System.out.println("No es posible realizar la transferencia desde la cuenta " + acnt
                        + ". Fondos insuficientes o límite de sobregiro excedido.");
                return -1;
            }

            // Realizar el retiro usando el método remoto withdraw de BankInterface
            boolean success = bank.withdraw(acnt, amt);

            if (success) {
                System.out.println("Retiro realizado exitosamente en la cuenta " + acnt + ".");
                newSaldo = bank.checkBalance(acnt);
            } else {
                System.out.println("El retiro de la cuenta " + acnt + " ha fallado.");
            }

        } catch (RemoteException e) {
            System.out.println("Error de comunicación con el banco: ");
            throw new NoBankOnline("No se puede realizar el retiro en el banco remoto.");
        } catch (NoAccountException e) {
            System.out.println("No existe la cuenta " + acnt);

        }

        return newSaldo;

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean transfer(BankInterface bankOri, int acntOri, BankInterface bankDes, int acntDes, double amt) {

        try {
            // Verificar si es posible realizar el retiro del monto especificado en la
            // cuenta de origen
            boolean possibleWithdraw = bankOri.isPossibleWithdraw(acntOri, amt);

            if (!possibleWithdraw) {
                System.out.println("No es posible realizar la transferencia desde la cuenta " + acntOri
                        + ". Fondos insuficientes o límite de sobregiro excedido.");
                return false;
            }

            // Realizar la transferencia usando el método remoto transfer de BankInterface
            bankOri.withdraw(acntOri, amt);
            bankDes.deposit(acntDes, amt);
            // boolean successTransfer = bankOri.transfer(acntOri, acntDes, amt);

            System.out.println("Transferencia exitosa desde la cuenta " + acntOri + " hacia la cuenta " + acntDes
                    + " por un monto de " + amt + ".");
            return true;

        } catch (RemoteException e) {
            System.out.println("Error de comunicación con el banco: " + e.getMessage());
        } catch (NoAccountException e) {
            System.out.println("No existe la cuenta " + acntOri);
        }

        return false;
    }

}
