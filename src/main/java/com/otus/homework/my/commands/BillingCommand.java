package com.otus.homework.my.commands;

public class BillingCommand extends Command {
    private String userID;
    private Integer amount;
    private String operID;

    public String getOperID() {
        return operID;
    }

    public void setOperID(String operID) {
        this.operID = operID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public BillingCommand(String userID) {
        super();
        this.userID = userID;
        this.amount = 0;
        this.operID = "";
    }

    public BillingCommand(String userID, Integer amount) {
        super();
        this.userID = userID;
        this.amount = amount;
        this.operID = "";
    }

    public BillingCommand(String userID, Integer amount, String operID) {
        super();
        this.userID = userID;
        this.amount = amount;
        this.operID = operID;
    }

    public BillingCommand() {
        super();
    }

    @Override
    public String toString() {
        return "{"
                + "userID=" + userID
                + ", amount=" + amount
                + ", operID=" + operID
                + '}';
    }
}
