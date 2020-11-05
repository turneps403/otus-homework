package com.otus.homework.my.events;

public class CreateBillingEvent extends Event {
    private String userID;

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    @Override
    public String toString() {
        return className + "{"
                + "eventID=" + eventID.toString()
                + ", created=" + created.toString()
                + ", className=" + className
                + ", userID=" + userID
                + '}';
    }
}
