package com.company.accounting;

import com.company.parameters.Parameters;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

public class AccountingDAO {
    public boolean writeUserToTable(Parameters param, EntityManager em) {
        try {
            em.getTransaction().begin();
            em.createNativeQuery("insert into pgsql.acc (login, autorityid, ds, de, vol) values (?,?,?,?,?) ")
                    .setParameter(1, param.getLogin())
                    .setParameter(2, param.getRole())
                    .setParameter(3, param.getDs())
                    .setParameter(4, param.getDe())
                    .setParameter(5, Long.parseLong(param.getVol()))
                    .executeUpdate();
            em.getTransaction().commit();
        } catch (NoResultException e) {
            return false;
        }
        return true;
    }

}
