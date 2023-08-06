package sd.grupo1.server.daoImp.jsonImp;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import sd.grupo1.server.dao.Dao;
import sd.grupo1.server.entities.Account;
import sd.grupo1.server.entities.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JsonFileUserDAO implements Dao {

    private final String filePath;
    private final String directory;
    private List<User> users;

    public JsonFileUserDAO(String directory, String filePath) {
        this.directory = directory;
        this.filePath = filePath;
        this.users = new ArrayList<User>();
    }

    private void readUsersFromJsonFile() {

        String jsonString = FileContentReader.readFromFile(directory, filePath);

        Gson gson = new Gson();

        // Deserializar objeto
        List<User> accountList = new ArrayList<User>();

        // verificamos si hay contenido en el archivo
        if (jsonString == null || jsonString.isEmpty()) {
            users = accountList;
        }
        accountList = Arrays.asList(gson.fromJson(jsonString, User[].class));
        users = accountList;
    }

    private void saveUsersToJsonFile() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(this.users);
        FileContentReader.saveToFile(directory, filePath, json);

    }

    @Override
    public List<User> getAllUsers() {
        readUsersFromJsonFile();
        return users;
    }

    @Override
    public List<Account> accountsUser(User user) {
        readUsersFromJsonFile();
        return user.getAccounts();
    }

    @Override
    public User createUser(String name, String lastName, String dni) {
        readUsersFromJsonFile();
        User newUser = new User(name, lastName, dni);
        users.add(newUser);
        saveUsersToJsonFile();
        return newUser;
    }

    @Override
    public User getUserById(int id) {
        readUsersFromJsonFile();
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    @Override
    public User getUserByDni(String dni) {
        readUsersFromJsonFile();
        for (User user : users) {
            if (user.getDni().equals(dni)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public void saveUser(User user) {
        readUsersFromJsonFile();
        users.add(user);
        saveUsersToJsonFile();
    }

    @Override
    public void updateUser(User user) {
        // readUsersFromJsonFile();
        int index = -1;
        for (int i = 0; i < users.size(); i++) {
            User u = users.get(i);
            if (u.getId() == user.getId()) {
                index = i;
                break;
            }
        }

        if (index != -1) {
            users.set(index, user);
            saveUsersToJsonFile();
        }
    }

    @Override
    public void deleteUser(User user) {
        readUsersFromJsonFile();
        users.remove(user);
        saveUsersToJsonFile();
    }

    @Override
    public List<Account> getAllAccounts() {
        readUsersFromJsonFile();
        List<Account> accounts = new ArrayList<Account>();

        for (User user : users) {
            for (Account account : user.getAccounts()) {
                accounts.add(account);
            }
        }

        return accounts;
    }

    @Override
    public Account createAccount(User user) {
        readUsersFromJsonFile();

        Account account = new Account(user);
        User ulista = getUserById(user.getId());
        ulista.addAccount(account);
        updateUser(ulista);

        saveUsersToJsonFile();

        return account;
    }

    @Override
    public Account getAccountByAccNum(int accNum) {
        readUsersFromJsonFile();
        for (User user : users) {
            for (Account account : user.getAccounts()) {
                if (account.getAcc_num() == accNum) {
                    return account;
                }
            }
        }
        return null;

    }

    @Override
    public void saveAccount(Account account) {
        readUsersFromJsonFile();
        User user = getUserById(account.getIdUser());
        user.addAccount(account);
        updateUser(user);

    }

    @Override
    public void updateAccount(Account account) {
        readUsersFromJsonFile();
        int index = -1;
        for (int i = 0; i < users.size(); i++) {
            User u = users.get(i);
            if (u.getId() == account.getIdUser()) {
                index = i;
                break;
            }
        }

        int index2 = -1;
        if (index!= -1) {
            User user = users.get(index);
            for (int j = 0; j < user.getAccounts().size();j++){
                if (user.getAccounts().get(j).getAcc_num() == account.getAcc_num()) {
                    index2 = j;
                    break;
                }
            }


            user.getAccounts().set(index2, account);
            updateUser(user);
        }

        // saveUsersToJsonFile();

    }

    @Override
    public void deleteAccount(Account account) {
        // readUsersFromJsonFile();
        User user = getUserById(account.getIdUser());
        user.getAccounts().remove(account);
        updateUser(user);

    }

    @Override
    public List<Account> userAccounts(User u) {
        List<Account> allAccounts = getAllAccounts();
        List<Account> userAccounts = new ArrayList<>();

        for (Account account : allAccounts) {
            if (account.getIdUser() == u.getId()) {
                userAccounts.add(account);
            }
        }

        return userAccounts;
    }
}
