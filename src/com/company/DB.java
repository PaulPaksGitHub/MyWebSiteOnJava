package com.company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

class Test {
    public static void main(String[] a)
            throws Exception {
        Connection conn = DriverManager.
                getConnection("jdbc:h2:~/test", "sa", "");
        Statement st = conn.createStatement();
        //st.execute("INSERT INTO users (login , pass) VALUES ('ha','34')");

        String sql = "select login from users where pass = 'pa'";
        ResultSet rs = st.executeQuery(sql);

        while (rs.next()) {
            String registeredUserLogin = rs.getString("login");

            // принтим в консоль логин, только что зарегистрированного пользователя
            System.out.println("User registered with login: " + registeredUserLogin);
        }

        conn.close();
    }
}