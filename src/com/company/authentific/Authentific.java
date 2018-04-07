package com.company.authentific;

import com.company.parametrs.Parameters;

public class Authentific {
    public boolean isAuthentificated(Parameters param) {
        try {
            Roles.roles.valueOf(param.getRole());
            ResourceParser user = new ResourceParser();
            if (user.authorizeFromAdress(param)) {
                return true;
            } else {
                System.exit(4);
                return false;
            }
        } catch (IllegalArgumentException e) {
            System.exit(3);
            return false;
        }
    }
}
