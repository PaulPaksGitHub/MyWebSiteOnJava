package com.company.parameters;

public class Parameters {
    private String login = "";
    private String pass = "";
    private String res = "";
    private String role = "";
    private String ds = "";
    private String de = "";
    private String vol = "";
    private boolean h = false;

    public boolean isEmpty() {
        return !canAutorize() && !canAuthehtific() && !canAccaunt();
    }

    public boolean hasLogin() {
        return !login.equals("");
    }

    public boolean hasPassword() {
        return !pass.equals("");
    }

    public boolean canAutorize() {
        return !login.equals("") || !pass.equals("");
    }

    public boolean canAuthehtific() {
        return !role.equals("") || !res.equals("");
    }

    public boolean canAccaunt() {
        return !ds.equals("") || !de.equals("") || !vol.equals("");
    }

    public String getLogin() {
        return login;
    }

    public String getPass() {
        return pass;
    }

    public String getRes() {
        return res;
    }

    public String getRole() {
        return role;
    }

    public String getDs() {
        return ds;
    }

    public String getDe() {
        return de;
    }

    public String getVol() {
        return vol;
    }

    public boolean isH() {
        return h;
    }

    public Parameters() {
        //Empty creator
    }

    public Parameters setLogin(String login) {
        Parameters.this.login = login;
        return this;
    }

    public Parameters setPass(String pass) {
        Parameters.this.pass = pass;
        return this;
    }

    public Parameters setRes(String res) {
        Parameters.this.res = res;
        return this;
    }

    public Parameters setRole(String role) {
        Parameters.this.role = role;
        return this;
    }

    public Parameters setDs(String ds) {
        Parameters.this.ds = ds;
        return this;
    }

    public Parameters setDe(String de) {
        Parameters.this.de = de;
        return this;
    }

    public Parameters setVol(String vol) {
        Parameters.this.vol = vol;
        return this;
    }

    public Parameters setH(boolean h) {
        Parameters.this.h = h;
        return this;
    }
}
