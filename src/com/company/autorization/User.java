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

    User(String login, String pass, String salt) {
        this.login = login;
        this.pass = pass;
        this.salt = salt;
    }
}
