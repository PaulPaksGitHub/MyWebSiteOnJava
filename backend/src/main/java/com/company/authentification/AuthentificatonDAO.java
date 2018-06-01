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

    public String getAll(Connection conn) throws SQLException {
        PreparedStatement st = conn.prepareStatement("select * from users");

        GsonBuilder builder = new GsonBuilder();
        builder.excludeFieldsWithoutExposeAnnotation().setPrettyPrinting();
        Gson gson = builder.create();

        ResultSet rs = st.executeQuery();
        List<User> list = new ArrayList<>();
        while (rs.next()) {
            User user = new User(
                    rs.getString("id"),
                    rs.getString("login"),
                    rs.getString("pass"),
                    rs.getString("salt"));
            list.add(user);
        }
        rs.close();
        st.close();
        return gson.toJson(list);
    }

    public String getUserFromID(Connection conn, String id) throws SQLException {
        PreparedStatement st = conn.prepareStatement("select * from users where userid = ?");
        st.setString(1, id);
        ResultSet rs = st.executeQuery();

        GsonBuilder builder = new GsonBuilder();
        builder.excludeFieldsWithoutExposeAnnotation();
        Gson gson = builder.create();

        if (rs.next()) {
            User user = new User(
                    rs.getString("id"),
                    rs.getString("login"),
                    rs.getString("pass"),
                    rs.getString("salt"));
            rs.close();
            st.close();
            return gson.toJson(user);
        }
        rs.close();
        st.close();
        return null;
    }
}
