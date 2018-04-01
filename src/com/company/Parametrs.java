package com.company;

public class Parametrs {
    //ARGUMENTS
    public String login = "";
    public String pass = "";
    public String res = "";
    public String role = "";
    public String ds = "";
    public String de = "";
    public String vol = "";

    //METHODS
    public boolean is_Empty() {
        return login.equals("") && pass.equals("");
    }

    public boolean hasLogin() {
        return !login.equals("");
    }

    public boolean hasRes() {
        return !res.equals("");
    }

    public boolean hasRole() {
        return !role.equals("");
    }

    public boolean hasDs() {
        return !ds.equals("");
    }

    public boolean hasDe() {
        return !de.equals("");
    }

    public boolean hasVol() {
        return !vol.equals("");
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

    //CREATING
    Parametrs(String log, String pas, String resource, String rol, String dts, String dte, String vl) {
        login = log;
        pass = pas;
        res = resource;
        role = rol;
        ds = dts;
        de = dte;
        vol = vl;
    }
}
