package com.company.autorization;

import com.company.parameters.Parameters;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Autorization {
    private static final Logger logger = LogManager.getLogger(Autorization.class);

    public void isAutorized(Parameters param, Connection  conn) throws SQLException {
        if (!param.hasLogin() && !param.hasPassword()) { //если логина и пароля нет нет, то работа программы завершается
            logger.error("Can not autorize: Hasn't login and password");
            conn.close();
            System.exit(6);

        } else if (param.hasLogin() && param.hasPassword()) {//введениы и логин, и пароль, то пытаемся авторизировать
            if (!isLoginRegex(param.getLogin())) {//если login не соответствует шаблону
                logger.error("Can not autorize: Login " + param.getLogin() + " isn't regex");
                conn.close();
                System.exit(1);
                return;
            }
            User userWithTheSameLogin = getUserFromLogin(param.getLogin(), conn);
            String password;

            try { //получаем хэш пароля, который ввел пользоаптель
                Md5Hash hash = new Md5Hash();
                password = hash.getHash(hash.getHash(param.getPass()) + userWithTheSameLogin.getSalt());

            } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
                logger.error("Can not autorize: Can't get password '" + param.getPass() + "' hash");
                logger.error(e);
                conn.close();
                System.exit(2);
                return;
            }

            if (!userWithTheSameLogin.getLogin().equals(param.getLogin())) { //если ne совпадает логин
                logger.error("Can not autorize:'" + param.getLogin() + "' wrong login");
                conn.close();
                System.exit(1);
            } else if (!userWithTheSameLogin.getPass().equals(password)) {//если ne совпадает пароль
                logger.error("Can not autorize: Password '" + param.getPass() + "' is wron for user " + param.getLogin());
                conn.close();
                System.exit(2);
            }

            logger.debug("Autorization success");

        } else if (!param.hasLogin()) {//если отсутствует логин
            logger.error("Can not autorize: Hasn't login");
            conn.close();
            System.exit(1);
        } else if (!param.hasPassword()) {//если отсутствует пароль
            logger.error("Can not autorize: Hasn't password");
            conn.close();
            System.exit(2);
        }
    }

    private User getUserFromLogin(String login, Connection conn) throws SQLException {
        PreparedStatement st = conn.prepareStatement("select * from users where login = ?");
        st.setString(1, login);

        ResultSet rs = st.executeQuery();

        if (rs.next()) {
            User user = new User(
                    rs.getString("login"),
                    rs.getString("pass"),
                    rs.getString("salt"));
            rs.close();
            st.close();
            return user;
        }
        rs.close();
        st.close();
        return new User("", "", "");
    }

    private boolean isLoginRegex(String login) {
        Pattern p = Pattern.compile("^[a-z0-9]+");
        Matcher m = p.matcher(login);
        return m.matches();
    }
}
