package com.otus.homework.my.commands;

public class OperationCommand extends Command {
    private String userID;
    private Integer amount;
    private String operID;

    public OperationCommand(String userID, Integer amount, String operID) {
        super();
        this.userID = userID;
        this.amount = amount;
        this.operID = operID;
    }

    public OperationCommand() {
        super();
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

    public String getOperID() {
        return operID;
    }

    public void setOperID(String operID) {
        this.operID = operID;
    }
}
