package com.otus.homework.my.dao;

import javax.persistence.*;

@Entity
//@Table(indexes = {
//    @Index(name = "user_list_operations", columnList = "userID", unique = true)
//})
public class OperationHistory {
    @Id
    private String userID;
    
    private String operID;

    public OperationHistory(String userID, String operID) {
        this.userID = userID;
        this.operID = operID;
    }

    public OperationHistory() {
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getOperID() {
        return operID;
    }

    public void setOperID(String operID) {
        this.operID = operID;
    }

    @Override
    public String toString() {
        return "User{" + "userID=" + userID + ", operID=" + operID +'}';
    }
}
