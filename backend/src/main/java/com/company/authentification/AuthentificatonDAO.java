package com.company.authentification;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;

public class AuthentificatonDAO {
    //    public User getUserFromLogin(String login, EntityManager em) throws SQLException {
//        PreparedStatement st = conn.prepareStatement("select * from users where login = ?");
//        st.setString(1, login);
//        ResultSet rs = st.executeQuery();
//        if (rs.next()) {
//            User user = new User(
//                    rs.getString("login"),
//                    rs.getString("pass"),
//                    rs.getString("salt"));
//            rs.close();
//            st.close();
//            return user;
//        }
//        rs.close();
//        st.close();
//        return null;
//    }

    public User getUserFromLogin(String login, EntityManager em) {
        User user = null;
        try {
            user = (User) em.createNativeQuery("select * from pgsql.users where login = ?", User.class)
                    .setParameter(1, login).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
        return user;
    }
}
