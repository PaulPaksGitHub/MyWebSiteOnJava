package com.company.authentification;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

public class AuthentificatonDAO {
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
