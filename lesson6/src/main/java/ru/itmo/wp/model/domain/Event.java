package ru.itmo.wp.model.domain;

import java.util.Date;

public class Event {
    private long id;
    private long userId;
    private Date creationTime;
    private Type type;

    public enum Type {
        ENTER, LOGOUT;
    }

    public Event() {
    }

    public Event(long userId, Type type) {
        this.userId = userId;
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
