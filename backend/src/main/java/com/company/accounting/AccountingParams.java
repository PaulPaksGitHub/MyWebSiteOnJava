package com.company.accounting;

import com.google.gson.annotations.Expose;

public class AccountingParams {
    @Expose() public String id;
    @Expose(serialize= false) public String login;
    @Expose(serialize= false) public String role;
    @Expose() public String ds;
    @Expose() public String de;
    @Expose() public String vol;


    public AccountingParams(String id, String login, String role, String ds, String de, String vol) {
        this.id = id;
        this.login = login;
        this.role = role;
        this.ds = ds;
        this.de = de;
        this.vol = vol;
    }
}
