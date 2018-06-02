package dao;

import com.company.authorization.AutorizationParams;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorizationDAO {
    public String getAll(Connection conn) throws SQLException {
        PreparedStatement st = conn.prepareStatement("select * from res");

        GsonBuilder builder = new GsonBuilder();
        builder.excludeFieldsWithoutExposeAnnotation().setPrettyPrinting();
        Gson gson = builder.create();

        ResultSet rs = st.executeQuery();
        List<AutorizationParams> list = new ArrayList<>();
        while (rs.next()) {
            AutorizationParams params = new AutorizationParams(
                    rs.getLong("id"),
                    rs.getString("adress"),
                    rs.getString("login"),
                    rs.getString("userid"),
                    rs.getString("role"));
            list.add(params);
        }
        rs.close();
        st.close();
        return gson.toJson(list);
    }

    public String getResFromID(Connection conn, String id) throws SQLException {
        PreparedStatement st = conn.prepareStatement("select * from res where id = ?");
        st.setString(1, id);
        ResultSet rs = st.executeQuery();

        GsonBuilder builder = new GsonBuilder();
        builder.excludeFieldsWithoutExposeAnnotation();
        Gson gson = builder.create();

        if (rs.next()) {
            AutorizationParams params = new AutorizationParams(
                    rs.getLong("id"),
                    rs.getString("adress"),
                    rs.getString("login"),
                    rs.getString("userid"),
                    rs.getString("role"));
            rs.close();
            st.close();
            return gson.toJson(params);
        }
        rs.close();
        st.close();
        return null;
    }

    public String getResFromUserID(Connection conn, String userid) throws SQLException {
        PreparedStatement st = conn.prepareStatement("select * from res where userid = ?");
        st.setString(1, userid);
        ResultSet rs = st.executeQuery();

        GsonBuilder builder = new GsonBuilder();
        builder.excludeFieldsWithoutExposeAnnotation().setPrettyPrinting();
        Gson gson = builder.create();

        List<AutorizationParams> list = new ArrayList<>();
        while (rs.next()) {
            AutorizationParams params = new AutorizationParams(
                    rs.getLong("id"),
                    rs.getString("adress"),
                    rs.getString("login"),
                    rs.getString("userid"),
                    rs.getString("role"));
            list.add(params);
        }
        rs.close();
        st.close();
        return gson.toJson(list);
    }

}
