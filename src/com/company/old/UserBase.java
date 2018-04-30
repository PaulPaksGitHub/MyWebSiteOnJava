package com.company.old;

import com.company.autorization.Md5Hash;
import com.company.autorization.User;
import com.company.parametrs.Parameters;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UserBase {
    public User getUserFromLogin(Parameters user) {
        List<User> list = new ArrayList<>();
        try {
            Md5Hash md5 = new Md5Hash();
            String salt;
            String pass;

            salt = generateSalt();
            pass = md5.getHash(md5.getHash("34") + salt);
            list.add(new User("ha", pass, salt));

            salt = generateSalt();
            pass = md5.getHash(md5.getHash("12") + salt);
            list.add(new User("pa", pass, salt));

            salt = generateSalt();
            pass = md5.getHash(md5.getHash("sup3rpaZZ") + salt);
            list.add(new User("jdoe", pass, salt));

            salt = generateSalt();
            pass = md5.getHash(md5.getHash("Qweqrty12") + salt);
            list.add(new User("jrow", pass, salt));

            salt = generateSalt();
            pass = md5.getHash(md5.getHash("yyy") + salt);
            list.add(new User("xxx", pass, salt));

        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            System.exit(6);
        }

        for (User i : list) {
            if (user.getLogin().equals(i.getLogin())) {
                return i;
            }
        }
        return list.get(0);
    }

    private String generateSalt() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 32) { // генерируем строку длиной 32
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        return salt.toString();

    }


}
