package sd.grupo1.server.entities;

import java.util.List;

public class User {

    private int id;
    private String nombre;
    private String apellido;
    private String dni;
    private List<Account> accounts;

    public User() {
        // Constructor vacío requerido por Hibernate
    }

    public User(int id, String nombre, String apellido, String dni, List<Account> accounts) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.accounts = accounts;
    }

    // Getters and Setters

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
}
