package com.company.authentification;

import com.google.gson.annotations.Expose;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name="users")
public class User {
    @Expose
    @Version
    @Column(name = "version")
    public long version;
    @Expose
    @Id
    @GeneratedValue
    @Column(name = "id")
    public long id;
    @Expose
    @Column(name = "login")
    public String login;
    @Expose(serialize = false)
    @Column(name = "pass")
    public String pass;
    @Expose(serialize = false)
    @Column(name = "salt")
    public String salt;

    public User(String login, String pass, String salt) {
        this.login = login;
        this.pass = pass;
        this.salt = salt;
    }

    public User(long id, String login, String pass, String salt) {
        this.id = id;
        this.login = login;
        this.pass = pass;
        this.salt = salt;
    }

    public User() {
    }
}
