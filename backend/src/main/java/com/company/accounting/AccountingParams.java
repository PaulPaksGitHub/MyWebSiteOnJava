package com.company.accounting;

import com.google.gson.annotations.Expose;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;

@Setter
@Getter
@Entity
public class AccountingParams {
    @Id
    @GeneratedValue
    long id;
    @Version
    long version;
    @Expose(serialize= false) public String login;
    @Expose(serialize= false) public String autorityid;
    @Expose public String ds;
    @Expose public String de;
    @Expose public String vol;


    public AccountingParams(long id, String login, String autorityid, String ds, String de, String vol) {
        this.id = id;
        this.login = login;
        this.autorityid = autorityid;
        this.ds = ds;
        this.de = de;
        this.vol = vol;
    }

    public AccountingParams() {
    }
}
