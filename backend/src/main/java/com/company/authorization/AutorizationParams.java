package com.company.authorization;

import com.google.gson.annotations.Expose;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class AutorizationParams {
    @Expose
    @Version
    long version;
    @Expose
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    long id;
    @Expose
    public String adress;
    @Expose(serialize = false)
    public String login;
    @Expose(serialize = false)
    public String userid;
    @Expose
    public String role;

    public AutorizationParams(long id, String adress, String login, String userid, String role) {
        this.id = id;
        this.adress = adress;
        this.login = login;
        this.userid = userid;
        this.role = role;
    }

    public AutorizationParams() {
    }
}