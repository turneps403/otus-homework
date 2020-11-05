package com.otus.homework.my.events;

import org.jetbrains.annotations.NotNull;

import java.util.Date;
import java.util.UUID;

public abstract class Event {
    public UUID eventID = UUID.randomUUID();
    public Long created = new Date().getTime();
    public String className;

    Event() {
        this.className = this.getClass().getName();
    }

    Event(@NotNull Event e) {
        this.eventID = e.getEventID();
        this.created = e.getCreated();
        this.className = e.getClassName();
    }

    public UUID getEventID() {
        return eventID;
    }

    public void setEventID(UUID eventID) {
        this.eventID = eventID;
    }

    public Long getCreated() {
        return created;
    }

    public void setCreated(Long created) {
        this.created = created;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    @Override
    public String toString() {
        return className + "{"
                + "eventID=" + eventID.toString()
                + ", created=" + created.toString()
                + ", className=" + className + '}';
    }
}
