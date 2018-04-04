package com.company.autorization;

import com.company.parametrs.Parametrs;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class UserBase {
    public User getUserFromLogin(Parametrs user) {
        List<User> list = new ArrayList<User>();
        try {
            Md5Hash password = new Md5Hash();
            String pass1 = password.getHash(password.getHash("12") + "f12f");
            list.add(new User("pa", pass1, "f12f"));
            String pass2 = password.getHash(password.getHash("34") + "q11q");
            list.add(new User("ha", pass2, "q11q"));
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
        }

        for (User i : list) {
            if (user.getLogin().equals(i.getLogin())) {
                return i;
            }
        }
        return list.get(0);
    }
}
