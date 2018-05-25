package com.company.authentification;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class User {
    private String login;
    private String pass;
    private String salt;

    public User(String login, String pass, String salt) {
        this.login = login;
        this.pass = pass;
        this.salt = salt;
    }
}
