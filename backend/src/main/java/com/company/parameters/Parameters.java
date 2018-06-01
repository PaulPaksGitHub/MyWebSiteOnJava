package com.company.parameters;

import lombok.Getter;
import lombok.Setter;
@Getter @Setter
public class Parameters {
    private String login;
    private String pass;
    private String res;
    private String role;
    private String ds;
    private String de;
    private String vol;
    private boolean h;

    public boolean isParamEmpty() {
        return !canAuthentific() && !canAuthorize() && !canAccaunt();
    }

    public boolean hasLogin() {
        return login != null;
    }

    public boolean hasPassword() {
        return pass != null;
    }

    public boolean hasNotRole() {
        return role == null;
    }

    public boolean hasNotRes() {
        return res == null;
    }

    public boolean canAuthentific() {
        return login != null || pass != null;
    }

    public boolean canAuthorize() {
        return role != null || res != null;
    }

    public boolean canAccaunt() {
        return ds != null || de != null || vol != null;
    }

    public boolean hasLoginAndPass() {
        return hasLogin() && hasPassword();
    }

    public boolean isH() {
        return h;
    }

    public Parameters() {
        //Empty constructor
    }

    public Parameters setLogin(String login) {
        if (login != null){
            Parameters.this.login = login.replace("'","");
        } else {
            Parameters.this.login = null;
        }
        return this;
    }

    public Parameters setPass(String pass) {
        if (pass != null){
            Parameters.this.pass = pass.replace("'","");
        } else {
            Parameters.this.pass = null;
        }
        return this;
    }

    public Parameters setRes(String res) {
        if (res != null) {
            Parameters.this.res = res.replace("'","");
        } else {
            Parameters.this.res = null;
        }
        return this;
    }

    public Parameters setRole(String role) {
        if (role != null) {
            Parameters.this.role = role.replace("'","");
        } else {
            Parameters.this.role = null;
        }
        return this;
    }

    public Parameters setDs(String ds) {
        if (ds != null) {
            Parameters.this.ds = ds.replace("'", "");
        } else {
            Parameters.this.ds = null;
        }
        return this;
    }

    public Parameters setDe(String de) {
        if (de != null) {
            Parameters.this.de = de.replace("'", "");
        } else {
            Parameters.this.de = null;
        }
        return this;
    }

    public Parameters setVol(String vol) {
        if (vol != null) {
            Parameters.this.vol = vol.replace("'", "");
        } else {
            Parameters.this.vol = null;
        }
        return this;
    }

    public Parameters setH(boolean h) {
        Parameters.this.h = h;
        return this;
    }
}
