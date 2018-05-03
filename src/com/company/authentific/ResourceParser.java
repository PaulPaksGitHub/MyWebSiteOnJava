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

    //из полного адреса ресурса создает частичный (начиная с родительского)
    //  и по получившемуся адресу проводит аутентификацию.
    //с каждой итерацией добавляет к частичному адресу следующий узел из полного адреса
    // и повторяет попыткум аутентификации.
    public boolean authentificFromAdress(Parameters param, Connection conn) throws SQLException {
        return hasPermission(param, param.getRes(), conn);
    }

    public boolean hasPermission(Parameters param, String cutAdress, Connection conn) throws SQLException {
        if (cutAdress == null) {
            logger.error("Authentification failed: user " + param.getLogin()
                    + " han't role " + param.getRole() + " on res " + param.getRes());
            return false;
        }
        PreparedStatement st = conn.prepareStatement(
                "select * from res where adress = ? and login = ? and role = ?");
        st.setString(1, cutAdress);
        st.setString(2, param.getLogin());
        st.setString(3, param.getRole());
        ResultSet rs = st.executeQuery();
        if (rs.next()) {
            rs.close();
            st.close();
            return true;
        } else {
            st = conn.prepareStatement("select * from res where adress = ?");
            st.setString(1, cutAdress);
            rs = st.executeQuery();
            return hasPermission(param, rs.getString("parent"), conn);
        }
    }
}
