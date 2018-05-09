package main.java.com.company.parameters;

public class Parameters {
    private String login = null;
    private String pass = null;
    private String res = null;
    private String role = null;
    private String ds = null;
    private String de = null;
    private String vol = null;
    private boolean h = false;

    public boolean isParamEmpty() {
        return !canAutorize() && !canAuthehtific() && !canAccaunt();
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

    public boolean canAutorize() {
        return login != null || pass != null;
    }

    public boolean canAuthehtific() {
        return role != null || res != null;
    }

    public boolean canAccaunt() {
        return ds != null || de != null || vol != null;
    }

    public boolean hasLoginAndPass() {
        return hasLogin() && hasPassword();
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
