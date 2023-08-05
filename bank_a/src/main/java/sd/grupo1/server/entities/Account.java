package sd.grupo1.server.entities;

import java.io.Serializable;

/**
 * Debe de representar una cuenta
 * Entro los atributos se tiene
 * private int acc_num;
 * private int acc_pin;
 * private int acc_bal;
 * private int acc_status;
 * 
 * 
 */

public class Account implements Serializable {

    private int acc_num;
    private int acc_pin;
    private double acc_bal;
    private int acc_status;

    public Account() {
        acc_num = 0;
        acc_pin = 0;
        acc_bal = 0;
        acc_status = 0;
    }

    public int getAcc_num() {
        return acc_num;
    }

    public void setAcc_num(int acc_num) {
        this.acc_num = acc_num;
    }

    public int getAcc_pin() {
        return acc_pin;
    }

    public void setAcc_pin(int acc_pin) {
        this.acc_pin = acc_pin;
    }

    public double getAcc_bal() {
        return acc_bal;
    }

    public void setAcc_bal(double acc_bal) {
        this.acc_bal = acc_bal;
    }

    public int getAcc_status() {
        return acc_status;
    }

    public void setAcc_status(int acc_status) {
        this.acc_status = acc_status;
    }
}
