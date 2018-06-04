package com.company.accounting;

import com.company.parameters.Parameters;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AccountingDAO {
    public boolean writeUserToTable(Parameters param, Connection conn) throws SQLException {
        try (PreparedStatement st = conn.prepareStatement("insert into acc (login, autorityid, ds, de, vol) values (?,?,?,?,?) ")) {
            st.setString(1, param.getLogin());
            st.setString(2, param.getRole());
            st.setString(3, param.getDs());
            st.setString(4, param.getDe());
            st.setString(5, param.getVol());
            st.executeUpdate();
            st.close();
            return true;
        } catch (SQLException e){
            return false;
        }
    }
}
