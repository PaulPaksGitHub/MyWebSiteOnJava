package com.company;

public class Parametrs {
    private String login = "";
    private String pass = "";
    private String res = "";
    private String role = "";
    private String ds = "";
    private String de = "";
    private String vol = "";

    public boolean is_Empty() {
        return login.equals("") && pass.equals("");
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

    Parametrs(String login, String pass, String res, String role, String ds, String de, String vol) {
        this.login = login;
        this.pass = pass;
        this.res = res;
        this.role = role;
        this.ds = ds;
        this.de = de;
        this.vol = vol;
    }
}
