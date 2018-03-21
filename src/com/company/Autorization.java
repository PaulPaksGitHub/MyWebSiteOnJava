package com.company;

public class Autorization {
    public static boolean autorize(Parametrs param) {

        if (param.is_Empty()) {
            System.exit(6);
            return false;
        }
        else if (param.hasLogin() && param.hasPassword()) {
            User gettedUser = UserBase.getUserFromLogin(param);
            if (gettedUser.login.equals(param.login) && gettedUser.pass.equals(param.pass)){
                return true;
            }
            else if (gettedUser.login != param.login){
                System.exit(1);
                return false;
            }
            else if (gettedUser.pass != param.pass){
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
