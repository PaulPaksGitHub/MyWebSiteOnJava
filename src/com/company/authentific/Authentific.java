package com.company.authentific;

import com.company.parametrs.Parameters;

public class Authentific {
    public void isAuthentificated(Parameters param) {
        try {
            Roles.roles.valueOf(param.getRole());
            ResourceParser user = new ResourceParser();
            if (!user.authorizeFromAdress(param)) {
                System.exit(4);
            }
        } catch (IllegalArgumentException e) {
            System.exit(3);
        }
    }
}
