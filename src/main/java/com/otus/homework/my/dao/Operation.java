package com.otus.homework.my.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Operation {
    @Id
    private String ID;

    private Integer amount;

    private String status = "created";

    public Long created = new Date().getTime();

    public Operation(String ID, Integer amount) {
        this.ID = ID;
        this.amount = amount;
    }

    public Operation() {
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getCreated() {
        return created;
    }

    public void setCreated(Long created) {
        this.created = created;
    }

    public void setStatusFailed() {
        this.setStatus("failed");
    }

    public void setStatusDone() {
        this.setStatus("done");
    }

    @Override
    public String toString() {
        return "Operation{" + "ID=" + ID + ", amount=" + amount + ", status=" + status + ", created="+ created +'}';
    }

}
