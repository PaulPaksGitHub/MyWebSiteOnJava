package dao;

import com.company.accounting.AccountingParams;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.inject.Inject;

import javax.persistence.EntityManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class AccountingDAO {
    @Inject EntityManager em;
    public String getAll(Connection conn) throws SQLException {
        PreparedStatement st = conn.prepareStatement("select * from acc");

        GsonBuilder builder = new GsonBuilder();
        builder.excludeFieldsWithoutExposeAnnotation().setPrettyPrinting();
        Gson gson = builder.create();

        ResultSet rs = st.executeQuery();
        List<AccountingParams> list = new ArrayList<>();
        while (rs.next()) {
            AccountingParams params = new AccountingParams(
                    rs.getLong("id"),
                    rs.getString("login"),
                    rs.getString("autorityid"),
                    rs.getString("ds"),
                    rs.getString("de"),
                    rs.getString("vol"));
            list.add(params);
        }
        rs.close();
        st.close();
        return gson.toJson(list);
    }

    public String getAccFromID(Connection conn, String id) throws SQLException {
        PreparedStatement st = conn.prepareStatement("select * from acc where id = ?");
        st.setString(1, id);
        ResultSet rs = st.executeQuery();

        GsonBuilder builder = new GsonBuilder();
        builder.excludeFieldsWithoutExposeAnnotation();
        Gson gson = builder.create();

        if (rs.next()) {
            AccountingParams params = new AccountingParams(
                    rs.getLong("id"),
                    rs.getString("login"),
                    rs.getString("autorityid"),
                    rs.getString("ds"),
                    rs.getString("de"),
                    rs.getString("vol"));
            rs.close();
            st.close();
            return gson.toJson(params);
        }
        rs.close();
        st.close();
        return null;
    }

    public String getAccFromAutorityID(Connection conn, String autorityid) throws SQLException {
        PreparedStatement st = conn.prepareStatement("select * from acc where autorityid = ?");
        st.setString(1, autorityid);
        ResultSet rs = st.executeQuery();

        GsonBuilder builder = new GsonBuilder();
        builder.excludeFieldsWithoutExposeAnnotation().setPrettyPrinting();
        Gson gson = builder.create();

        List<AccountingParams> list = new ArrayList<>();
        while (rs.next()) {
            AccountingParams params = new AccountingParams(
                    rs.getLong("id"),
                    rs.getString("login"),
                    rs.getString("autorityid"),
                    rs.getString("ds"),
                    rs.getString("de"),
                    rs.getString("vol"));
            list.add(params);
        }
        rs.close();
        st.close();
        return gson.toJson(list);
    }
}
