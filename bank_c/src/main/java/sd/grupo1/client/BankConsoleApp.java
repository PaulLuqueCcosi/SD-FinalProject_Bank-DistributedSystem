package sd.grupo1.client;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import sd.grupo1.client.dto.BankAccountDTO;
import sd.grupo1.client.dtoImp.BankAccount;
import sd.grupo1.client.service.InterfaceSD;
import sd.grupo1.exception.NoAccountException;
import sd.grupo1.exception.NoBankOnline;
import sd.grupo1.server.dto.AccountUserDTO;
import sd.grupo1.server.service.BankInterface;

public class BankConsoleApp {

    private InterfaceSD bankService;
    HashMap<Integer, BankAccount> map = new HashMap<>();
    private List<BankAccountDTO> listaBancos = new ArrayList<BankAccountDTO>();
    String dni = "73057755";

    public BankConsoleApp(InterfaceSD bankService) {
        this.bankService = bankService;
    }

    private boolean login() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese su número de DNI: ");
        dni = scanner.nextLine();

        // System.out.print("Ingrese su contraseña: ");
        // String pin = scanner.nextLine();
        // this.dni = "73057755";
        return true;

    }

    private void salir() {
        System.out.println("Hasta luego");
        System.exit(0);
    }

    private void menu() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nMenú de opciones:");
            System.out.println("1. Listado de cuentas");
            System.out.println("2. Realizar depósito");
            System.out.println("3. Realizar retiro");
            System.out.println("4. Realizar transferencia");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            int option = -1;
            try {
                option = Integer.parseInt(scanner.nextLine());

            } catch (NumberFormatException e) {
                System.out.println("Opción inválida. Intente nuevamente.");
                continue;
            }

            switch (option) {
                case 1:
                    listAllCounts();
                    break;

                case 2:
                    performDeposit();
                    break;
                case 3:
                    performWithdraw();
                    break;
                case 4:
                    performTransfer();
                    break;
                case 0:
                    salir();
                    break;
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
            }
        }
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Bienvenido al sistema bancario distribuido.");
        boolean loggedIn = false;
        while (!loggedIn) {
            loggedIn = login();
        }
        System.out.println("Bienvenido al sistema :  " + dni);

        menu();

    }

    private void update() {
        listaBancos = bankService.getAllAcount(dni);

    }

    private void listAllCounts() {
        // test
        update();

        if (listaBancos == null) {
            System.out.println("DNI no encontrado en ninguno de los bancos.");
            return;
        }

        System.out.println("Listado de Bancos");

        for (BankAccountDTO bank : listaBancos) {
            System.out.println("*************************************************************");
            System.out.println(bank.getName());
            System.out.println(bank.getLocation());
            for (AccountUserDTO account : bank.getAccounts()) {
                System.out.println("--------------------------------------------------------");
                System.out.println("Numero de cuenta: \t" + account.getNumberAccount());
                System.out.println("Nombre: \t" + account.getNameUserAccount());
                System.out.println("Apellidos:t " + account.getLastNameUserAccount());
                System.out.println("Balance: \t" + account.getBalanceAccount());
            }
            System.out.println();
        }

    }

    private void performDeposit() {
        HashMap<Integer, BankAccount> map = new HashMap<Integer, BankAccount>();
        update();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Seleccione a la cuenta a la cual desea depositar []");

        int i = 0;
        for (BankAccountDTO bankAccountDTO : listaBancos) {
            for (AccountUserDTO accountUser : bankAccountDTO.getAccounts()) {
                i++;

                map.put(i, new BankAccount(bankAccountDTO.getBank(), accountUser));

                String texto = "[" + i + "] ";
                texto += bankAccountDTO.getName() + " - ";
                texto += accountUser.getNumberAccount() + " (";
                texto += accountUser.getBalanceAccount() + ")";

                System.out.println(texto);

            }
        }

        System.out.println("[-1] Atras");
        System.out.println();

        System.out.print("[?]: ");
        int indice = Integer.parseInt(scanner.nextLine());

        if (indice == -1) {
            return;
        }

        int nuemroDeCuenta = map.get(indice).getAcc().getNumberAccount();
        BankInterface bank = map.get(indice).getBank();
        System.out.printf("Cuenta Numero : %s , seleccionada del banco %s", nuemroDeCuenta,
                map.get(indice).getNameBank());
        System.out.println();

        System.out.print("Ingrese el monto a depositar: ");
        double amount = Double.parseDouble(scanner.nextLine());

        try {
            bank.deposit(nuemroDeCuenta, amount);
        } catch (RemoteException e) {
            System.out.println("No se pudo conectar con el banco");
            e.printStackTrace();
        } catch (NoAccountException e) {
            System.out.println("Numero de cuenta no existe");
        }

        try {
            System.out.println("Depósito realizado. \nNuevo saldo: " + bank.checkBalance(nuemroDeCuenta));
        } catch (RemoteException e) {
            System.out.println("No se pudo conectar con el banco");
            e.printStackTrace();
        } catch (NoAccountException e) {
            System.out.println("Numero de cuenta no existe");

        }

    }

    private void performWithdraw() {
        HashMap<Integer, BankAccount> map = new HashMap<Integer, BankAccount>();
        update();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Seleccione a la cuenta a la cual desea retirar []");

        int i = 0;
        for (BankAccountDTO bankAccountDTO : listaBancos) {
            for (AccountUserDTO accountUser : bankAccountDTO.getAccounts()) {
                i++;

                map.put(i, new BankAccount(bankAccountDTO.getBank(), accountUser));

                String texto = "[" + i + "] ";
                texto += bankAccountDTO.getName() + " - ";
                texto += accountUser.getNumberAccount() + " (";
                texto += accountUser.getBalanceAccount() + ")";

                System.out.println(texto);

            }
        }

        System.out.println("[-1] Atras");
        System.out.println();

        System.out.print("[?]: ");
        int indice = Integer.parseInt(scanner.nextLine());

        if (indice == -1) {
            return;
        }

        int nuemroDeCuenta = map.get(indice).getAcc().getNumberAccount();
        BankInterface bank = map.get(indice).getBank();
        System.out.println("\n");
        System.out.printf("Cuenta Numero : %s , seleccionada del banco %s", nuemroDeCuenta,
                map.get(indice).getNameBank());
        System.out.println("\n");

        System.out.print("Ingrese el monto a retirar: ");
        double amount = Double.parseDouble(scanner.nextLine());

        // verificamos si se puede retirar

        try {
            boolean isPosible = bank.isPossibleWithdraw(nuemroDeCuenta, amount);
            if (isPosible) {
                System.out.print("Ingrese el PIN de la cuenta: ");
                String pin = scanner.nextLine();
                int inpin = Integer.parseInt(pin);

                boolean isPin = bank.checkAccPin(nuemroDeCuenta, inpin);

                if (isPin) {
                    System.out.println("Procesando el retiro...");
                    bank.withdraw(nuemroDeCuenta, amount);
                    System.out.println("Retiro realizado. \nNuevo saldo: " + bank.checkBalance(nuemroDeCuenta));
                    return;
                } else {
                    System.out.println("PIN incorrecto");
                }
            } else {
                System.out.println("Saldo en la cuenta no es suficiente: " + bank.checkBalance(nuemroDeCuenta));
            }
        } catch (RemoteException e) {
            System.out.println("No se pudo conectar con el banco");

        } catch (NoAccountException e) {
            System.out.println("Numero de cuenta no existe");
        }

    }

    private void performTransfer() {
        HashMap<Integer, BankAccount> map = new HashMap<Integer, BankAccount>();
        update();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Seleccione a la cuenta a la cual desea retirar []");

        int i = 0;
        for (BankAccountDTO bankAccountDTO : listaBancos) {
            for (AccountUserDTO accountUser : bankAccountDTO.getAccounts()) {
                i++;

                map.put(i, new BankAccount(bankAccountDTO.getBank(), accountUser));

                String texto = "[" + i + "] ";
                texto += bankAccountDTO.getName() + " - ";
                texto += accountUser.getNumberAccount() + " (";
                texto += accountUser.getBalanceAccount() + ")";

                System.out.println(texto);

            }
        }

        System.out.println("[-1] Atras");
        System.out.println();

        System.out.print("[?]: ");
        int indice1 = Integer.parseInt(scanner.nextLine());

        if (indice1 == -1) {
            return;
        }

        int nuemroDeCuenta1 = map.get(indice1).getAcc().getNumberAccount();
        BankInterface bank1 = map.get(indice1).getBank();
        System.out.println("\n");
        System.out.printf("Cuenta Numero : %s , seleccionada del banco %s", nuemroDeCuenta1,
                map.get(indice1).getNameBank() + "\n");

        // ingresa el mondo a retirar
        System.out.print("Ingrese el monto a retirar: ");
        double amount = Double.parseDouble(scanner.nextLine());

        boolean isPosible = false;
        try {
            isPosible = bank1.isPossibleWithdraw(nuemroDeCuenta1, amount);
        } catch (RemoteException | NoAccountException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if (!isPosible) {
            try {
                System.out.println("Saldo en la cuenta no es suficiente: " + bank1.checkBalance(nuemroDeCuenta1));
            } catch (RemoteException | NoAccountException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return;
        }

        // ingreso de la contrasena

        System.out.print("Ingrese el PIN de la cuenta: ");
        String pin = scanner.nextLine();
        int inpin = Integer.parseInt(pin);
        boolean isPin = false;
        try {
            isPin = bank1.checkAccPin(nuemroDeCuenta1, inpin);
        } catch (RemoteException | NoAccountException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        if (!isPin) {
            System.out.println("PIN incorrecto");
            return;
        }

        //////////////////

        System.out.println("Seleccione a la cuenta a la cual desea depositar []");

        int j = 0;
        for (BankAccountDTO bankAccountDTO : listaBancos) {
            for (AccountUserDTO accountUser : bankAccountDTO.getAccounts()) {
                j++;

                String texto = "[" + j + "] ";
                texto += bankAccountDTO.getName() + " - ";
                texto += accountUser.getNumberAccount() + " (";
                texto += accountUser.getBalanceAccount() + ")";

                System.out.println(texto);

            }
        }
        System.out.println("[-1] Atras");
        System.out.println();

        System.out.print("[?]: ");
        int indice2 = Integer.parseInt(scanner.nextLine());

        if (indice2 == -1) {
            return;
        }

        if (indice1 == indice2) {
            System.out.println("No se puede transferir entre las mismas cuentas");
            return;
        }

        int nuemroDeCuenta2 = map.get(indice2).getAcc().getNumberAccount();
        BankInterface bank2 = map.get(indice2).getBank();

        System.out.println("\n");
        System.out.printf("Cuenta Numero : %s , seleccionada del banco %s", nuemroDeCuenta2,
                map.get(indice2).getNameBank());
        System.out.println("\n");

        // porcesando transferencia
        System.out.println("Procesando el trasnferencia...");
        try {
            bankService.transfer(bank1, nuemroDeCuenta1, bank2, nuemroDeCuenta2, amount);
        } catch (NoBankOnline e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.printf("Retiro transferencia realizasa");
        return;

    }

}
