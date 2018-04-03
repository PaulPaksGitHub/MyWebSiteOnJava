package com.company.autorization;

public class User {
    private String login;
    private String pass;
    private String salt;

    public String getLogin() {
        return login;
    }

    public String getPass() {
        return pass;
    }

    public String getSalt() {
        return salt;
    }

    User(String log, String pas, String sal) {
        login = log;
        pass = pas;
        salt = sal;
    }
}
