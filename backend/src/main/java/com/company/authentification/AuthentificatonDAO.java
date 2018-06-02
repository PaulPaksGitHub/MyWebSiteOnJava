package com.company.authentification;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthentificatonDAO {
    public User getUserFromLogin(String login, Connection conn) throws SQLException {
        PreparedStatement st = conn.prepareStatement("select * from users where login = ?");
        st.setString(1, login);
        ResultSet rs = st.executeQuery();
        if (rs.next()) {
            User user = new User(
                    rs.getString("login"),
                    rs.getString("pass"),
                    rs.getString("salt"));
            rs.close();
            st.close();
            return user;
        }
        rs.close();
        st.close();
        return null;
    }
}
