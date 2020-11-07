package com.otus.homework.my.dao;

import javax.persistence.*;

@Entity
public class Bill {
    @Id
    private String userID;

    private Integer balance;

    public Bill(String userID, Integer balance) {
        this.userID = userID;
        this.balance = balance;
    }

    public Bill() {
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "bill{" + "userID=" + userID + ", balance=" + balance + '}';
    }
}
