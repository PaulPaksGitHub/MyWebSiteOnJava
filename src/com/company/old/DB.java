package com.company.old;

import com.company.autorization.Md5Hash;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.Random;

class CreateUserBase {
    public static void main(String[] a) {

        try (Connection conn = DriverManager.
                getConnection("jdbc:h2:~/prj/db", "sa", "");) {
            Statement st = conn.createStatement();
            //ResultSet rs = st.executeQuery("insert into users (login,pass,salt) values ()");
            //PreparedStatement statement =
                    //conn.prepareStatement("INSERT INTO messages (author, text) VALUES (?, ?);");
            Md5Hash md5 = new Md5Hash();
            String salt;
            String pass;

            salt = generateSalt();
            pass = md5.getHash(md5.getHash("34") + salt);
            ResultSet rs = st.executeQuery("insert into users (login,pass,salt) values ('ha','"+pass+"','"+salt+"')");

            salt = generateSalt();
            pass = md5.getHash(md5.getHash("12") + salt);
            rs = st.executeQuery("insert into users (login,pass,salt) values ('pa',"+pass+","+salt+")");

            salt = generateSalt();
            pass = md5.getHash(md5.getHash("sup3rpaZZ") + salt);
            rs = st.executeQuery("insert into users (login,pass,salt) values ('jdoe',"+pass+","+salt+")");

            salt = generateSalt();
            pass = md5.getHash(md5.getHash("Qweqrty12") + salt);
            rs = st.executeQuery("insert into users (login,pass,salt) values ('jrow',"+pass+","+salt+")");

            salt = generateSalt();
            pass = md5.getHash(md5.getHash("yyy") + salt);
            rs = st.executeQuery("insert into users (login,pass,salt) values ('xxx',"+pass+","+salt+")");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    private static String generateSalt() {
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