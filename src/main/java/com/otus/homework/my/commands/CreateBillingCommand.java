package com.otus.homework.my.commands;

public class CreateBillingCommand extends Command {
    private String userID;

    public CreateBillingCommand(String userID) {
        this.userID = userID;
    }

    public CreateBillingCommand() {
        super();
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
}
