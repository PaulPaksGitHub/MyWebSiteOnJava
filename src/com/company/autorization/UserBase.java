package com.company.autorization;

import com.company.Parametrs;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class UserBase {
    //Give user from login
    public static User getUserFromLogin(Parametrs user) {
        List<User> list = new ArrayList<User>();
        try {
            String pass1 = Md5Hash.getHash(Md5Hash.getHash("12") + "f12f");
            //System.out.println(pass1);
            list.add(new User("pa", pass1, "f12f"));
            String pass2 = Md5Hash.getHash(Md5Hash.getHash("34") + "q11q");
            list.add(new User("ha", pass2, "q11q"));
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
        }

        for (User i : list) {
            if (user.login.equals(i.login)) {
                return i;
            }
        }
        return list.get(0);
    }
}
