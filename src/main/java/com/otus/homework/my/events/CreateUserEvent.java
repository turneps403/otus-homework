package com.otus.homework.my.events;

import org.jetbrains.annotations.NotNull;

public class CreateUserEvent extends Event {
    public String userID;
    public String firstName;
    public String lastName;

//    public CreateUserEvent() {
//        super();
//    }
//
//    public CreateUserEvent(@NotNull Event e) {
//        super(e);
//        this.userID = ((CreateUserEvent) e).getUserID();
//        this.firstName = ((CreateUserEvent) e).getFirstName();
//        this.lastName = ((CreateUserEvent) e).getLastName();
//    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return className + "{"
                + "eventID=" + eventID.toString()
                + ", created=" + created.toString()
                + ", className=" + className
                + ", userID=" + userID
                + ", firstName=" + firstName
                + ", lastName=" + lastName
                + '}';
    }
}
