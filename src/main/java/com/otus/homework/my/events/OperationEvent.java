package com.otus.homework.my.events;

public class OperationEvent extends Event {
    private String userID;
    private Integer amount;
    private String operID;

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

    public OperationEvent(String userID, Integer amount, String operID) {
        super();
        this.userID = userID;
        this.amount = amount;
        this.operID = operID;
    }

    public OperationEvent() {
        super();
    }

    @Override
    public String toString() {
        return className + "{"
                + "eventID=" + eventID.toString()
                + ", created=" + created.toString()
                + ", className=" + className
                + ", userID=" + userID
                + ", amount=" + amount
                + ", orderID=" + operID
                + '}';
    }
}
