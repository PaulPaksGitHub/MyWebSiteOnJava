package dao;

import com.company.authentification.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.inject.Inject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import servlet.GuiceListtener;

import javax.persistence.EntityManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthentificatonDAO {
    private EntityManager em;

    @Inject
    public AuthentificatonDAO(EntityManager em) {
        this.em = em;
    }

    private Logger logger = LogManager.getLogger(GuiceListtener.class);

    public String getAll(Connection conn) throws SQLException {
        PreparedStatement st = conn.prepareStatement("select * from users");

        GsonBuilder builder = new GsonBuilder();
        builder.excludeFieldsWithoutExposeAnnotation().setPrettyPrinting();
        Gson gson = builder.create();

        ResultSet rs = st.executeQuery();
        List<User> list = new ArrayList<>();
        while (rs.next()) {
            User user = new User(
                    rs.getLong("id"),
                    rs.getString("login"),
                    rs.getString("pass"),
                    rs.getString("salt"));
            list.add(user);
        }
        rs.close();
        st.close();
        return gson.toJson(list);
    }

    public String getUserFromEM(Connection conn, String id) {
        GsonBuilder builder = new GsonBuilder();
        builder.excludeFieldsWithoutExposeAnnotation();
        Gson gson = builder.create();
        logger.error(gson.toJson(em));

        em.getTransaction().begin();


        logger.debug(em.createQuery("SELECT u FROM User u WHERE u.id = :id")
                .setParameter("id", Long.parseLong(id)).getResultList());

        String text = gson.toJson(em.createQuery("SELECT u FROM User u WHERE u.id = :userid")
                .setParameter("userid", id).getResultList());

        em.getTransaction().commit();
        em.close();
        return text;
    }

    public String getUserFromID(Connection conn, String id) throws SQLException {
        PreparedStatement st = conn.prepareStatement("select * from users where id = ?");
        st.setString(1, id);
        ResultSet rs = st.executeQuery();

        GsonBuilder builder = new GsonBuilder();
        builder.excludeFieldsWithoutExposeAnnotation();
        Gson gson = builder.create();

        if (rs.next()) {
            User user = new User(
                    rs.getLong("id"),
                    rs.getString("login"),
                    rs.getString("pass"),
                    rs.getString("salt"));
            rs.close();
            st.close();
            return gson.toJson(user);
        }
        rs.close();
        st.close();
        return null;
    }
}
