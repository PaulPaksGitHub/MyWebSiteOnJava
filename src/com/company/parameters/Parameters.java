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

    public Builder newBuilder() {
        return new Parameters().new Builder();
    }

    public Parameters() {
        //Empty creator
    }

    public class Builder {
        private Builder() {
            //Empty creator
        }

        public Builder setLogin(String login) {
            Parameters.this.login = login;
            return this;
        }

        public Builder setPass(String pass) {
            Parameters.this.pass = pass;
            return this;
        }

        public Builder setRes(String res) {
            Parameters.this.res = res;
            return this;
        }

        public Builder setRole(String role) {
            Parameters.this.role = role;
            return this;
        }

        public Builder setDs(String ds) {
            Parameters.this.ds = ds;
            return this;
        }

        public Builder setDe(String de) {
            Parameters.this.de = de;
            return this;
        }

        public Builder setVol(String vol) {
            Parameters.this.vol = vol;
            return this;
        }

        public Builder setH(boolean h) {
            Parameters.this.h = h;
            return this;
        }

        public Parameters build() {
            return Parameters.this;
        }

    }
}
