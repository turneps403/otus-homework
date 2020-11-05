package com.otus.homework.my.dao;

import javax.persistence.*;

@Entity
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ID;

    @Column(unique=true)
    private String userID;

    private Integer balance;

    public Bill(Long ID, String userID, Integer balance) {
        this.ID = ID;
        this.userID = userID;
        this.balance = balance;
    }

    public Bill() {
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
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
        return "User{" + "ID=" + ID + ", userID=" + userID + ", balance=" + balance + '}';
    }
}
