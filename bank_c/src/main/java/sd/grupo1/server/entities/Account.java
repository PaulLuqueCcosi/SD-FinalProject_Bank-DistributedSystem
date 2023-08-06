package sd.grupo1.server.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
@Entity
@Table(name = "Account")
public class Account implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "acc_num")
    private int accNum;

    @Column(name = "acc_pin", nullable = false)
    private int accPin;

    @Column(name = "acc_bal", nullable = false)
    private double accBal;

    @Column(name = "acc_status", nullable = false)
    private int accStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private int idUser;

    public Account() {
        accNum = 0;
        accPin = 0;
        accBal = 0;
        accStatus = 0;
        idUser = -1;
    }

    public Account(User u) {
        accNum = 0;
        accPin = 0;
        accBal = 0;
        accStatus = 0;
        idUser = u.getId();
        // u.addAccount(this);
    }

    public int getAcc_num() {
        return accNum;
    }

    public void setAcc_num(int acc_num) {
        this.accNum = acc_num;
    }

    public int getAcc_pin() {
        return accPin;
    }

    public void setAcc_pin(int acc_pin) {
        this.accPin = acc_pin;
    }

    public double getAcc_bal() {
        return accBal;
    }

    public void setAcc_bal(double acc_bal) {
        this.accBal = acc_bal;
    }

    public int getAcc_status() {
        return accStatus;
    }

    public void setAcc_status(int acc_status) {
        this.accStatus = acc_status;
    }

    public void setIUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdUser() {
        return idUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Account account = (Account) o;
        return accNum == account.accNum;
    }

    @Override
        public String toString() {
            return "Account{" +
                    "accNum=" + accNum +
                    ", accPin=" + accPin +
                    ", accBal=" + accBal +
                    ", accStatus=" + accStatus +
                    ", idUser=" + idUser +
                    '}';
        }

}
