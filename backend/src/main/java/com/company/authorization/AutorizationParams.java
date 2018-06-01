package com.company.authorization;

import com.google.gson.annotations.Expose;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;

@Getter
@Setter
public class AutorizationParams {
    @Id
    @GeneratedValue
    @Expose
    long entityid;
    @Version
    @Expose
    long version;
    @Expose public String autorizationid;
    @Expose public String adress;
    @Expose(serialize = false) public String login;
    @Expose(serialize = false) public String userid;
    @Expose public String role;

    public AutorizationParams(String id, String adress, String login,String userid, String role) {
        this.autorizationid = id;
        this.adress = adress;
        this.login = login;
        this.userid = userid;
        this.role = role;
    }
}