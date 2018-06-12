package com.company.accounting;

import com.company.authentification.User;
import com.company.parameters.Parameters;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AccountingDAO {
//    public boolean writeUserToTable(Parameters param, Connection conn) throws SQLException {
//        try (PreparedStatement st = conn.prepareStatement("insert into acc (login, autorityid, ds, de, vol) values (?,?,?,?,?) ")) {
//            st.setString(1, param.getLogin());
//            st.setString(2, param.getRole());
//            st.setString(3, param.getDs());
//            st.setString(4, param.getDe());
//            st.setString(5, param.getVol());
//            st.executeUpdate();
//            st.close();
//            return true;
//        } catch (SQLException e){
//            return false;
//        }
//    }

    public boolean writeUserToTable(Parameters param, EntityManager em) {
        try {
            em.getTransaction().begin();
            em.createNativeQuery("insert into pgsql.acc (login, autorityid, ds, de, vol) values (?,?,?,?,?) ")
                    .setParameter(1, param.getLogin())
                    .setParameter(2, param.getRole())
                    .setParameter(3, param.getDs())
                    .setParameter(4, param.getDe())
                    .setParameter(5, param.getVol())
                    .executeUpdate();
            em.getTransaction().commit();
        } catch (NoResultException e) {
            return false;
        }
        return true;
    }

}
