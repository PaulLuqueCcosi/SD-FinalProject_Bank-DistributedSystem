package sd.grupo1.server.daoImp;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import sd.grupo1.server.dao.DaoUser;
import sd.grupo1.server.entities.Account;
import sd.grupo1.server.entities.User;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonFileUserDAO implements DaoUser {

    private final String filePath;
    private List<User> users;

    public JsonFileUserDAO(String filePath) {
        this.filePath = filePath;
        this.users = readUsersFromJsonFile();
    }

    private List<User> readUsersFromJsonFile() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(new File(filePath), new TypeReference<List<User>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private void saveUsersToJsonFile() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(new File(filePath), users);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAll() {
        return users;
    }

    @Override
    public List<Account> accountsUser(User user) {
        return user.getAccounts();
    }

    @Override
    public User createUser(String name, String lastName, String dni) {
        User newUser = new User(name, lastName, dni);
        users.add(newUser);
        saveUsersToJsonFile();
        return newUser;
    }

    @Override
    public User getUserById(int id) {
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    @Override
    public User getUserByDni(String dni) {
        for (User user : users) {
            if (user.getDni().equals(dni)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public void saveUser(User user) {
        users.add(user);
        saveUsersToJsonFile();
    }

    @Override
    public void updateUser(User user) {
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
        users.remove(user);
        saveUsersToJsonFile();
    }
}
