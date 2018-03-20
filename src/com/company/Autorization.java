package com.company;

public class Autorization {
    public static boolean autorize(Parametrs param) {

        if (param.is_Empty()) {
            System.out.println("Is empty");
            System.exit(6);
            return false;
        }
        else if (param.hasLogin() && param.hasPassword()) {
            System.out.println("Autorization");
            User gettedUser = UserBase.getUserFromLogin(param.login);
            System.out.println(gettedUser.login + gettedUser.pass);
            if (gettedUser.login == param.login && gettedUser.pass==param.pass){
                System.out.println("Autorized");
                return true;
            }
            else if (gettedUser.login != param.login){
                System.out.println("Wrong login");
                System.exit(1);
                return false;
            }
            else if (gettedUser.pass != param.pass){
                System.out.println("Wrong pass");
                System.exit(2);
                return false;
            }
        }
        else if (!param.hasLogin()){
            System.out.println("Hasn't login");
            System.exit(1);
            return false;
        }
        else if (!param.hasPassword()){
            System.out.println("Hasn't pass");
            System.exit(2);
            return false;
        }
        return false;
    }
}
