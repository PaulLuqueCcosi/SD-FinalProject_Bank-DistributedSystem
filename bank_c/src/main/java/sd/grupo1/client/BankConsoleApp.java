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
    String dni = "123";

    public BankConsoleApp(InterfaceSD bankService) {
        this.bankService = bankService;
    }

    private boolean login() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese su número de DNI: ");
        dni = scanner.nextLine();

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
            System.out.println("Seleccione una opción: ");
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
        update();

        if (listaBancos == null) {
            System.out.println("DNI : " + dni + "no encontrado en ninguno de los bancos.");
            return;
        }

        System.out.println("Listado de Bancos");

        for (BankAccountDTO bank : listaBancos) {
            System.out.println("\n");
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
        map = new HashMap<Integer, BankAccount>();
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

        int selectedOption = -1;

        while (true) {
            System.out.print("[?]: ");
            String input = scanner.nextLine();

            try {
                selectedOption = Integer.parseInt(input);

                if (selectedOption == -1) {
                    return;
                }

                if (selectedOption < 1 || selectedOption > i) {
                    System.out.println("Opción inválida. Intente nuevamente o ingrese -1 para salir.");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Intente nuevamente o ingrese -1 para salir.");
            }
        }

        int nuemroDeCuenta = map.get(selectedOption).getAcc().getNumberAccount();
        BankInterface bank = map.get(selectedOption).getBank();
        System.out.printf("Cuenta Numero : %s , seleccionada del banco %s", nuemroDeCuenta,
                map.get(selectedOption).getNameBank());
        System.out.println();

        // ingreso del monto y deposito
        while (true) {
            System.out.print("Ingrese el monto a depositar (o ingrese 'exit' para cancelar): ");
            String amountInput = scanner.nextLine();

            if (amountInput.equalsIgnoreCase("exit")) {
                return;
            }

            try {
                double amount = Double.parseDouble(amountInput);

                if (amount <= 0) {
                    System.out.println("Monto inválido. Debe ser mayor que cero.");
                } else {
                    try {
                        bankService.deposit(bank, nuemroDeCuenta, amount);
                        // System.out.println("Depósito realizado exitosamente.");
                    } catch (NoBankOnline e) {
                        System.out.println("Depósito NO realizado debido a problemas en el banco.");
                    }
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Intente nuevamente o ingrese 'exit' para cancelar.");
            }
        }

    }

    private void performWithdraw() {

        map = new HashMap<Integer, BankAccount>();
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

        int selectedOption = -1;

        while (true) {
            System.out.print("[?]: ");
            String input = scanner.nextLine();

            try {
                selectedOption = Integer.parseInt(input);

                if (selectedOption == -1) {
                    return;
                }

                if (selectedOption < 1 || selectedOption > i) {
                    System.out.println("Opción inválida. Intente nuevamente o ingrese -1 para salir.");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Intente nuevamente o ingrese -1 para salir.");
            }
        }

        int nuemroDeCuenta = map.get(selectedOption).getAcc().getNumberAccount();
        BankInterface bank = map.get(selectedOption).getBank();
        System.out.printf("Cuenta Numero : %s , seleccionada del banco %s", nuemroDeCuenta,
                map.get(selectedOption).getNameBank());
        System.out.println();

        // ingreso del monto y deposito
        while (true) {
            System.out.print("Ingrese el monto a retirar (o ingrese 'exit' para cancelar): ");
            String amountInput = scanner.nextLine();

            if (amountInput.equalsIgnoreCase("exit")) {
                return;
            }

            try {
                double amount = Double.parseDouble(amountInput);

                if (amount <= 0) {
                    System.out.println("Monto inválido. Debe ser mayor que cero.");
                } else {
                    try {
                        bankService.withdraw(bank, nuemroDeCuenta, amount);
                        // System.out.println("Retiro realizado exitosamente.");
                    } catch (NoBankOnline e) {
                        System.out.println("Depósito NO realizado debido a problemas en el banco.");
                    }
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Intente nuevamente o ingrese 'exit' para cancelar.");
            }
        }

    }

    private void performTransfer() {

        map = new HashMap<Integer, BankAccount>();
        update();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Seleccione a la cuenta a la cual desea Retirar []");

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

        int selectedOption = -1;

        while (true) {
            System.out.print("[?]: ");
            String input = scanner.nextLine();

            try {
                selectedOption = Integer.parseInt(input);

                if (selectedOption == -1) {
                    return;
                }

                if (selectedOption < 1 || selectedOption > i) {
                    System.out.println("Opción inválida. Intente nuevamente o ingrese -1 para salir.");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Intente nuevamente o ingrese -1 para salir.");
            }
        }

        int nuemroDeCuentaOrigen = map.get(selectedOption).getAcc().getNumberAccount();
        BankInterface bankOrigen = map.get(selectedOption).getBank();
        System.out.printf("Cuenta Numero : %s , seleccionada del banco %s", nuemroDeCuentaOrigen,
                map.get(selectedOption).getNameBank());
        System.out.println();

        // ingreso datos destino

        System.out.println("Seleccione a la cuenta a la cual desea Depositar []");

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

        int selectedOption2 = -1;

        while (true) {
            System.out.print("[?]: ");
            String input2 = scanner.nextLine();

            try {
                selectedOption2 = Integer.parseInt(input2);

                if (selectedOption2 == -1) {
                    return;
                }

                if (selectedOption2 < 1 || selectedOption2 > j) {
                    System.out.println("Opción inválida. Intente nuevamente o ingrese -1 para salir.");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Intente nuevamente o ingrese -1 para salir.");
            }
        }

        // verifcamos si es la misma cuenta
        if (selectedOption == selectedOption2) {
            System.out.println("Las cuentas seleccionadas son iguales");
            return;
        }

        int nuemroDeCuentaDes = map.get(selectedOption2).getAcc().getNumberAccount();
        BankInterface bankDes = map.get(selectedOption2).getBank();
        System.out.printf("Cuenta Numero : %s , seleccionada del banco %s", nuemroDeCuentaDes,
                map.get(selectedOption2).getNameBank());
        System.out.println();

        double amount = -1;
        // ingreso del monto y deposito
        while (true) {
            System.out.print("Ingrese el monto a transferir (o ingrese 'exit' para cancelar): ");
            String amountInput = scanner.nextLine();

            if (amountInput.equalsIgnoreCase("exit")) {
                return;
            }

            try {
                amount = Double.parseDouble(amountInput);

                if (amount <= 0) {
                    System.out.println("Monto inválido. Debe ser mayor que cero.");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Intente nuevamente o ingrese 'exit' para cancelar.");
            }
        }

        // realizando transferencia

        try {
            bankService.transfer(bankOrigen, nuemroDeCuentaOrigen, bankDes, nuemroDeCuentaDes, amount);
        } catch (NoBankOnline e) {
            System.out.println("Transferencia NO realizada");
            e.printStackTrace();
        }
    }

}
