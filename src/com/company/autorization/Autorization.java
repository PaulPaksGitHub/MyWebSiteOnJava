package com.company.autorization;

import com.company.Parametrs;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public class Autorization {
    public boolean autorize(Parametrs param) {

        if (param.is_Empty()) {
            System.exit(6);
            return false;
        } else if (param.hasLogin() && param.hasPassword()) {
            UserBase userBibl = new UserBase();
            User gettedUser = userBibl.getUserFromLogin(param);
            String password = "";
            try {
                Md5Hash pass = new Md5Hash();
                password = pass.getHash(pass.getHash(param.pass) + gettedUser.salt);
                //System.out.println(password);
            } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
                System.exit(2);
                return false;
            }

            if (gettedUser.login.equals(param.login) && gettedUser.pass.equals(password)) {
                return true;
            } else if (!gettedUser.login.equals(param.login)) {
                System.exit(1);
                return false;
            } else if (!gettedUser.pass.equals(password)) {
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
