package com.company;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public class Autorization {
    public static boolean autorize(Parametrs param) {

        if (param.is_Empty()) {
            System.exit(6);
            return false;
        }
        else if (param.hasLogin() && param.hasPassword()) {
            User gettedUser = UserBase.getUserFromLogin(param);
            String password = "";
            try {
                password = Md5Hash.getHash(Md5Hash.getHash(param.pass)+gettedUser.salt);
                //System.out.println(password);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            if (gettedUser.login.equals(param.login) && gettedUser.pass.equals(password)){
                return true;
            }
            else if (gettedUser.login != param.login){
                System.exit(1);
                return false;
            }
            else if (gettedUser.pass != password){
                System.exit(2);
                return false;
            }
        }
        else if (!param.hasLogin()){
            System.exit(1);
            return false;
        }
        else if (!param.hasPassword()){
            System.exit(2);
            return false;
        }
        return false;
    }
}
