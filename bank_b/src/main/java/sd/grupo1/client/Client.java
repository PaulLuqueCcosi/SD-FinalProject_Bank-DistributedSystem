package sd.grupo1.client;

import java.security.Policy;

import sd.grupo1.client.service.InterfaceSD;
import sd.grupo1.client.serviceImp.InterfaceSDImp;

public class Client extends Policy {
    String directory = "BD_C/";
    String bdFileUser = "bd_c_user.json";
    String bdFileAccount = "bd_c_account.json";

    private Client() {
    }

    public void startCliente() {
        InterfaceSD interfase = new InterfaceSDImp();

        BankConsoleApp app = new BankConsoleApp(interfase);
        app.run();
    }

    public static void main(String[] args) {

        System.out.println("Iniciando CLiente");
        Client client = new Client();
        client.startCliente();

    }
}