package com.company;

import java.util.ArrayList;
import java.util.List;

public class Autorization {
    public static void autorize(Parametrs param) {
        List<User> list = new ArrayList<User>();
        list.add(new User("pa", "12"));
        list.add(new User("ha", "34"));
        System.out.println("List has created");

        if (param.is_Empty()) System.out.println("Is empty");
        else if (param.hasLogin() && param.hasPassword()) {
            System.out.println("Autorization");
        }
        else if (!param.hasLogin()){
            System.out.println("Hasn't login");
        }
        else if (!param.hasPassword()){
            System.out.println("Hasn't pass");
        }
    }
}
