package com.company.authentification;

import com.google.gson.annotations.Expose;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;

@Getter
@Setter
@Entity
public class User {
    @Id
    @GeneratedValue
    @Expose
    long entityid;
    @Expose
    @Version
    long version;
    @Expose
    public String userid;
    @Expose
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

    public User(String userid, String login, String pass, String salt) {
        this.userid = userid;
        this.login = login;
        this.pass = pass;
        this.salt = salt;
    }
}
