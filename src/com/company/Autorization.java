package com.company;

public class Autorization {
    public static void autorize(Parametrs param) {

        if (param.is_Empty()) System.out.println("Is empty");
        else if (param.hasLogin() && param.hasPassword()) {
            System.out.println("Autorization");
            User gettedUser = UserBase.getUserFromLogin(param.login);
            System.out.println(gettedUser.login + gettedUser.pass);
            if (gettedUser.login == param.login && gettedUser.pass==param.pass){
                System.out.println("Autorized");
            }
            else if (gettedUser.login != param.login){
                System.out.println("Wrong login");
            }
            else if (gettedUser.pass != param.pass){
                System.out.println("Wrong pass");
            }
        }
        else if (!param.hasLogin()){
            System.out.println("Hasn't login");
        }
        else if (!param.hasPassword()){
            System.out.println("Hasn't pass");
        }
    }
}
