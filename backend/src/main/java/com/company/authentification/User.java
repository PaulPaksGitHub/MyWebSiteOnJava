package com.company.authentification;

import com.google.gson.annotations.Expose;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
    @Expose
    @Version
    @Column(name = "id")
    long version;
    @Expose
    @Id
    @GeneratedValue
    long id;
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

    public User(long userid, String login, String pass, String salt) {
        this.id = userid;
        this.login = login;
        this.pass = pass;
        this.salt = salt;
    }

    public User() {
    }
}
