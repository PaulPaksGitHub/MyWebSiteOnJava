package com.company.accounting;

import com.company.parameters.Parameters;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AccountingDAO {
    public boolean writeUserToTable(Parameters param, Connection conn) throws SQLException {
        PreparedStatement st = conn.prepareStatement("insert into acc (login, ds, de, vol) values (?,?,?,?) ");
        st.setString(1, param.getLogin());
        st.setString(2, param.getDs());
        st.setString(3, param.getDe());
        st.setString(4, param.getVol());
        st.executeUpdate();
        st.close();
        return true;
    }
}
