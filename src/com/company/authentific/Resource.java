package com.company.authentific;

public class Resource {
    private String adress;
    private String login;
    private String role;

    public String getLogin() {
        return login;
    }

    public String getRole() {
        return role;
    }

    public String getAdress() {
        return adress;
    }

    Resource(String adress, String login, String role) {
        this.adress = adress;
        this.login = login;
        this.role = role;
    }
}
