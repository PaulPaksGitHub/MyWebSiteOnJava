package com.company.authentification;

import com.google.gson.annotations.Expose;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    @Expose()
    public String id = null;
    @Expose()
    public String login;
    @Expose(serialize = false)
    public String pass;
    @Expose(serialize = false)
    public String salt;

    public User(String login, String pass, String salt) {
        this.login = login;
        this.pass = pass;
        this.salt = salt;
    }

    public User(String id, String login, String pass, String salt) {
        this.id = id;
        this.login = login;
        this.pass = pass;
        this.salt = salt;
    }
}
