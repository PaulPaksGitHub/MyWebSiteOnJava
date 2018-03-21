package com.company;

import java.util.ArrayList;
import java.util.List;

public class UserBase {
    public static User getUserFromLogin(Parametrs user){
        List<User> list = new ArrayList<User>();
        list.add(new User("pa", "12"));
        list.add(new User("ha", "34"));
        for (User i : list){
            if (user.login.equals(i.login)){
                return i;
            }
        }
        return list.get(0);
    }
}
