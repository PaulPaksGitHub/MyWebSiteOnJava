package com.company.authorization;

import com.company.authentification.User;
import com.company.parameters.Parameters;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorizationDAO {
//    public boolean getAccessToRes(Parameters param, Connection conn) throws SQLException {
//        PreparedStatement st = conn.prepareStatement(
//                "select * from res where (adress like concat(?,'%'))and (concat(?,'.') like concat(res.adress,'.%')) and login = ? and role = ?");
//        st.setString(1, param.getRes().split("\\.")[0]);
//        st.setString(2, param.getRes());
//        st.setString(3, param.getLogin());
//        st.setString(4, param.getRole());
//        ResultSet rs = st.executeQuery();
//        if (rs.next()) {
//            st.close();
//            rs.close();
//            return true;
//        }
//        st.close();
//        rs.close();
//        return false;
//    }

    public boolean getAccessToRes(Parameters param, EntityManager em) {
        try {
            AutorizationParams params = (AutorizationParams) em.createNativeQuery("select * from pgsql.res where (adress like concat(?,'%'))and (concat(?,'.') like concat(res.adress,'.%')) and login = ? and role = ?", AutorizationParams.class)
                    .setParameter(1, param.getRes().split("\\.")[0])
                    .setParameter(2, param.getRes())
                    .setParameter(3, param.getLogin())
                    .setParameter(4, param.getRole())
                    .getSingleResult();
        } catch (NoResultException e) {
            return false;
        }
        return true;
    }
}
