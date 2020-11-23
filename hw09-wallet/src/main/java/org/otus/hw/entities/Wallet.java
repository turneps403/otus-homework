package org.otus.hw.entities;

import javax.persistence.*;

@Entity
@Table(name = "Wallet")
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userID")
    private Integer userID;

    @Column(name = "balance")
    private Integer balance;

    public Wallet(Integer userID, Integer balance) {
        this.userID = userID;
        this.balance = balance;
    }

    public Wallet() {
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }
}
