package com.otus.homework.my.commands;

public class CreateBillingCommand extends BillingCommand {
    public CreateBillingCommand(String userID) {
        super(userID);
    }

    public CreateBillingCommand() {
        super();
    }
}
