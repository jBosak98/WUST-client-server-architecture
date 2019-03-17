package com.jbosak.csa.bank.server;

import java.io.Serializable;

public class User implements Serializable {

    private String username;

    private String password;

    private Integer money;

    public User() { }

    public User(String username, String password, Integer money) {
        this.username = username;
        this.password = password;
        this.money = money;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.money = 0;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", money=" + money +
                '}';
    }
}
