package com.company.autorization;

public class User {
    public String login;
    public String pass;
    public String salt;

    User(String log, String pas, String sal) {
        login = log;
        pass = pas;
        salt = sal;
    }
}
