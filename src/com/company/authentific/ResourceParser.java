package com.company.authentific;

import com.company.parameters.Parameters;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ResourceParser {
    private static final Logger logger = LogManager.getLogger(ResourceParser.class);

    public boolean authentificFromAdressOneSQL(Parameters param, Connection conn) throws SQLException {
        PreparedStatement st = conn.prepareStatement(
                "select * from res where (adress like concat(?,'%'))and (concat(?,'.') like concat(res.adress,'.%')) and login = ? and role = ?");
        st.setString(1, param.getRes().split("\\.")[0]);
        st.setString(2, param.getRes());
        st.setString(3, param.getLogin());
        st.setString(4, param.getRole());
        ResultSet rs = st.executeQuery();
        if (rs.next()) {
            System.out.println(rs.getString("adress"));
            rs.close();
            st.close();
            return true;
        }
        rs.close();
        st.close();
        return false;

    }
}
