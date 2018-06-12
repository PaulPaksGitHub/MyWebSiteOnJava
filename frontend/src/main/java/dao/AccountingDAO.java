package dao;

import com.company.accounting.AccountingParams;
import com.google.gson.Gson;
import com.google.inject.Inject;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;
import java.sql.SQLException;
import java.util.List;

public class AccountingDAO {
    private EntityManager em;
    private Gson gson;
    private Logger logger;

    @Inject
    public AccountingDAO(EntityManager em, Gson gson, Logger logger) {
        this.em = em;
        this.gson = gson;
        this.logger = logger;
    }

    public String getAll() throws SQLException {
        List list = em.createNativeQuery("select * from acc", AccountingParams.class)
                .getResultList();
        String text = gson.toJson(list);
        logger.error(text);
        return text;
    }

    public String getAccFromID(String id) throws SQLException {
        List list = em.createNativeQuery("select * from acc where id = ?", AccountingParams.class)
                .setParameter(1, id).getResultList();
        String text = gson.toJson(list);
        logger.error(text);
        return text;
    }

    public String getAccFromAutorityID(String autorityid) throws SQLException {
        List list = em.createNativeQuery("select * from pgsql.acc where autorityid = ?", AccountingParams.class)
                .setParameter(1, autorityid).getResultList();
        String text = gson.toJson(list);
        logger.error(text);
        return text;
    }
}
