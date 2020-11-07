package com.otus.homework.my.commands;

public class TopUpBillingCommand extends BillingCommand {
    public TopUpBillingCommand(String userID, Integer amount) {
        super(userID, amount);
    }

    public TopUpBillingCommand() {
        super();
    }
}
