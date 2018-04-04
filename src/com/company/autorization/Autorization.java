package com.company.autorization;

import com.company.Parametrs;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public class Autorization {
    public boolean isAutorized(Parametrs param) {
        if (param.is_Empty()) {
            System.exit(6);
            return false;
        } else if (param.hasLogin() && param.hasPassword()) {
            UserBase userBibl = new UserBase();
            User gettedUser = userBibl.getUserFromLogin(param);
            String password = "";
            try {
                Md5Hash pass = new Md5Hash();
                password = pass.getHash(pass.getHash(param.getPass()) + gettedUser.getSalt());
            } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
                System.exit(2);
                return false;
            }

            if (gettedUser.getLogin().equals(param.getLogin()) && gettedUser.getPass().equals(password)) {
                return true;
            } else if (!gettedUser.getLogin().equals(param.getLogin())) {
                System.exit(1);
                return false;
            } else if (!gettedUser.getPass().equals(password)) {
                System.exit(2);
                return false;
            }
        } else if (!param.hasLogin()) {
            System.exit(1);
            return false;
        } else if (!param.hasPassword()) {
            System.exit(2);
            return false;
        }
        return false;
    }
}
