package com.company.authorization;

import com.company.parameters.Parameters;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

public class AuthorizationDAO {
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
