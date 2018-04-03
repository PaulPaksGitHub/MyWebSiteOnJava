package com.company.authentific;

import com.company.Parametrs;

public class Authentific {
    private enum roles {READ, WRITE, EXECUTE}

    public boolean auth(Parametrs param) {
        boolean accept = true;
        try {
            roles.valueOf(param.getRole());
        } catch (IllegalArgumentException e) {
            System.exit(3);
            accept = false;
        }
        if (accept) {
            ResourceParser user = new ResourceParser();
            if (user.authorizeFromAdress(param)) {
                return true;
            } else {
                System.exit(4);
                return false;
            }
        } else {
            System.exit(3);
            return false;
        }
    }
}
