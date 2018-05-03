package com.company.authentific;

import com.company.parameters.Parameters;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

public class ResourceParser {
    private static final Logger logger = LogManager.getLogger(ResourceParser.class);

    //из полного адреса ресурса создает частичный (начиная с родительского)
    //  и по получившемуся адресу проводит аутентификацию.
    //с каждой итерацией добавляет к частичному адресу следующий узел из полного адреса
    // и повторяет попыткум аутентификации.
    public boolean authentificFromAdress(Parameters param, Connection conn) throws SQLException {
        String fullAdress = param.getRes();
        String[] subStr;
        String delimeter = "\\.";
        subStr = fullAdress.split(delimeter);
        String newStr = "";
        for (String aSubStr : subStr) {
            newStr += aSubStr;
            if (hasPermission(param, newStr, conn)) {
                return true;
            }
            newStr += ".";
        }
        logger.error("Authentification failed: user " + param.getLogin()
                + " han't role " + param.getRole() + " on res " + param.getRes());
        return false;
    }

    public boolean hasPermission(Parameters param, String cutAdress, Connection conn) throws SQLException {
        PreparedStatement st = conn.prepareStatement("select * from res where adress = ?");
        st.setString(1, cutAdress);
        ResultSet rs = st.executeQuery();

        while (rs.next()) {
            if (rs.getString("login").equals(param.getLogin()) &&
                    rs.getString("role").equals(param.getRole())) {
                rs.close();
                st.close();
                return true;
            }
        }
        rs.close();
        st.close();
        return false;
    }
}
