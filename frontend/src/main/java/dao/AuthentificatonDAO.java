package dao;

import com.company.authentification.User;
import com.google.gson.Gson;
import com.google.inject.Inject;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;
import java.sql.SQLException;
import java.util.List;

public class AuthentificatonDAO {
    private EntityManager em;
    private Gson gson;
    private Logger logger;

    @Inject
    public AuthentificatonDAO(EntityManager em, Gson gson, Logger logger) {
        this.em = em;
        this.gson = gson;
        this.logger = logger;
    }

    public String getAll() throws SQLException {
        List list = em.createNativeQuery("select * from pgsql.users", User.class).getResultList();
        String text = gson.toJson(list);
        logger.error(text);
        return text;
    }

    public String getUserFromEM(String id) {
        List list = em.createNativeQuery("select * from pgsql.users where id = ?", User.class)
                .setParameter(1, id).getResultList();
        String text = gson.toJson(list);
        logger.error(text);
        return text;
    }
}
//    ========================= Old Method Realization ===========================
//
//    public String getUserFromID(Connection conn, String id) throws SQLException {
//        PreparedStatement st = conn.prepareStatement("select * from users where id = ?");
//        st.setString(1, id);
//        ResultSet rs = st.executeQuery();
//
//        GsonBuilder builder = new GsonBuilder();
//        builder.excludeFieldsWithoutExposeAnnotation();
//        Gson gson = builder.create();
//
//        if (rs.next()) {
//            User user = new User(
//                    rs.getLong("id"),
//                    rs.getString("login"),
//                    rs.getString("pass"),
//                    rs.getString("salt"));
//            rs.close();
//            st.close();
//            return gson.toJson(user);
//        }
//        rs.close();
//        st.close();
//        return null;
//    }

