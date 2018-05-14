package com.company.authorization;

import com.company.parameters.Parameters;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorizationDAO {
    public boolean getAccessToRes(Parameters param, Connection conn) throws SQLException {
        PreparedStatement st = conn.prepareStatement(
                "select * from res where (adress like concat(?,'%'))and (concat(?,'.') like concat(res.adress,'.%')) and login = ? and role = ?");
        st.setString(1, param.getRes().split("\\.")[0]);
        st.setString(2, param.getRes());
        st.setString(3, param.getLogin());
        st.setString(4, param.getRole());
        ResultSet rs = st.executeQuery();
        st.close();
        rs.close();
        if (rs.next()) {
            return true;
        }
        return false;
    }
}
