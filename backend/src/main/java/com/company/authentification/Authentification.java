package com.company.authentification;

import com.company.SysExits;
import com.company.parameters.Parameters;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Authentification {
    private static final Logger logger = LogManager.getLogger(Authentification.class);
    private AuthentificatonDAO dao;

    public SysExits isAuthentificable(Parameters param, EntityManager em) throws SQLException {
        if (!param.hasLogin()) {//если отсутствует логин
            logger.error("Can not autorize: Hasn't login");
            return SysExits.EXIT1;
        } else if (!param.hasPassword()) {//если отсутствует пароль
            logger.error("Can not autorize: Hasn't password");
            return SysExits.EXIT2;
        } else if (!param.hasLoginAndPass()) { //если и логина и пароля нет
            logger.error("Can not autorize: Hasn't login and password");
            return SysExits.EXIT6;

        } else if (param.hasLoginAndPass()) {//введениы и логин, и пароль, то пытаемся авторизировать
            if (!isLoginRegex(param.getLogin())) {//если login не соответствует шаблону
                logger.error("Can not autorize: Login {} isn't regex", param.getLogin());
                return SysExits.EXIT1;
            }

            User userWithTheSameLogin = dao.getUserFromLogin(param.getLogin(), em);

            if (userWithTheSameLogin == null) {
                logger.error("Can not autorize: {} is wrong login", param.getLogin());
                return SysExits.EXIT1;
            }
            String password = "";

            try { //получаем хэш пароля, который ввел пользоаптель
                Md5Hash hash = new Md5Hash();
                password = hash.getHash(hash.getHash(param.getPass()) + userWithTheSameLogin.getSalt());

            } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
                logger.error("Can not autorize: Can't get hash of pass {]", param.getPass());
                logger.error(e);
                return SysExits.EXIT2;
            }

            if (!userWithTheSameLogin.getLogin().equals(param.getLogin())) { //если ne совпадает логин
                logger.error("Can not autorize: {} is wrong login", param.getLogin());
                return SysExits.EXIT1;
            } else if (!userWithTheSameLogin.getPass().equals(password)) {//если ne совпадает пароль
                logger.error("Can not autorize: Password {} is wrong for user {}", param.getPass(), param.getLogin());
                return SysExits.EXIT2;
            }
        }
        logger.info("Authentification success for user {}", param.getLogin());
        return SysExits.EXIT0;
    }

    public boolean isLoginRegex(String login) {
        Pattern p = Pattern.compile("^[a-zA-Z0-9]+");
        Matcher m = p.matcher(login);
        return m.matches();
    }

    public Authentification(AuthentificatonDAO dao) {
        this.dao = dao;
    }

    public Authentification() {
        this.dao = new AuthentificatonDAO();
    }
}
