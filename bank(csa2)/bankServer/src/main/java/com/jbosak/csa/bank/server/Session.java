package com.jbosak.csa.bank.server;

import java.sql.Timestamp;
import java.util.Date;

public class Session {

    private Timestamp expirationTime;
    private User user;

    public Session(User user) {
        this.user = user;
        expirationTime = new Timestamp((new Date().getTime() + 1000000));
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Timestamp getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(Timestamp expirationTime) {
        this.expirationTime = expirationTime;
    }
}
