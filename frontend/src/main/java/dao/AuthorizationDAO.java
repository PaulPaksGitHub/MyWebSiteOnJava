package dao;

import com.company.authorization.AutorizationParams;
import com.google.gson.Gson;
import com.google.inject.Inject;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;
import java.sql.SQLException;
import java.util.List;

public class AuthorizationDAO {
    private EntityManager em;
    private Gson gson;
    private Logger logger;

    @Inject
    public AuthorizationDAO(EntityManager em, Gson gson, Logger logger) {
        this.em = em;
        this.gson = gson;
        this.logger = logger;
    }

    public String getAll() throws SQLException {
        List list = em.createNativeQuery("select * from pgsql.res", AutorizationParams.class)
                .getResultList();
        String text = gson.toJson(list);
        logger.error(text);
        return text;
    }

    public String getResFromID(String id) throws SQLException {
        List list = em.createNativeQuery("select * from pgsql.res where id = ?", AutorizationParams.class)
                .setParameter(1, id).getResultList();
        String text = gson.toJson(list);
        logger.error(text);
        return text;
    }

    public String getResFromUserID(String userid) throws SQLException {
        List list = em.createNativeQuery("select * from pgsql.res where userid = ?", AutorizationParams.class)
                .setParameter(1, Long.parseLong(userid)).getResultList();
        String text = gson.toJson(list);
        logger.error(text);
        return text;
    }
}
