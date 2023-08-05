package sd.grupo1.server.daoImp;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import sd.grupo1.server.dao.DaoAccount;
import sd.grupo1.server.entities.Account;
import sd.grupo1.server.entities.User;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonFileAccountDAO implements DaoAccount {

    private final String filePath;
    private List<Account> accounts;

    public JsonFileAccountDAO(String filePath) {
        this.filePath = filePath;
        this.accounts = readAccountsFromJsonFile();
    }

    private List<Account> readAccountsFromJsonFile() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(new File(filePath), new TypeReference<List<Account>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private void saveAccountsToJsonFile() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(new File(filePath), accounts);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Account> getAll() {
        return accounts;
    }

    @Override
    public Account getAccountByAccNum(int accNum) {
        for (Account account : accounts) {
            if (account.getAcc_num() == accNum) {
                return account;
            }
        }
        return null;
    }

    @Override
    public void saveAccount(Account account) {
        accounts.add(account);
        saveAccountsToJsonFile();
    }

    @Override
    public void updateAccount(Account account) {
        int index = -1;
        for (int i = 0; i < accounts.size(); i++) {
            Account acc = accounts.get(i);
            if (acc.getAcc_num() == account.getAcc_num()) {
                index = i;
                break;
            }
        }

        if (index != -1) {
            accounts.set(index, account);
            saveAccountsToJsonFile();
        }
    }

    @Override
    public void deleteAccount(Account account) {
        accounts.remove(account);
        saveAccountsToJsonFile();
    }

    @Override
    public List<Account> userAccounts(User u) {
        List<Account> allAccounts = getAll();
        List<Account> userAccounts = new ArrayList<>();

        for (Account account : allAccounts) {
            if (account.getIdUser().equals(u)) {
                userAccounts.add(account);
            }
        }

        return userAccounts;
    }
}
