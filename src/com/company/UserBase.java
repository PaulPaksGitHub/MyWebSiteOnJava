package com.company;

import java.util.ArrayList;

public class UserBase {
    public static User getUserFromLogin(String curr_login){
        ArrayList<User> list;
        list = new ArrayList<User>();
        list.add(new User("pa", "12"));
        System.out.println(list.get(0).login);
        list.add(new User("ha", "34"));
        System.out.println(list.get(0).login);
        System.out.println(list.get(1).login);
        for (int i = 0; i < list.size(); i++){
            if (curr_login==list.get(i).login){
                return list.get(i);
            }
        }
        return list.get(0);
    }
}
