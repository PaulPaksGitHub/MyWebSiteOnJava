package main.java.com.company.authentification;

import main.java.com.company.SysExits;
import main.java.com.company.parameters.Parameters;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Authentification {
    private static final Logger logger = LogManager.getLogger(Authentification.class);

    public SysExits isAuthentificable(Parameters param, Connection conn) throws SQLException {
        if (!param.hasLogin()) {//если отсутствует логин
            logger.error("Can not autorize: Hasn't login");
            return SysExits.valueOf("EXIT1");
        } else if (!param.hasPassword()) {//если отсутствует пароль
            logger.error("Can not autorize: Hasn't password");
            return SysExits.valueOf("EXIT2");
        } else if (!param.hasLoginAndPass()) { //если и логина и пароля нет
            logger.error("Can not autorize: Hasn't login and password");
            return SysExits.valueOf("EXIT6");

        } else if (param.hasLoginAndPass()) {//введениы и логин, и пароль, то пытаемся авторизировать
            if (!isLoginRegex(param.getLogin())) {//если login не соответствует шаблону
                logger.error("Can not autorize: Login {} isn't regex", param.getLogin());
                return SysExits.valueOf("EXIT1");
            }

            User userWithTheSameLogin = getUserFromLogin(param.getLogin(), conn);
            String password = "";

            try { //получаем хэш пароля, который ввел пользоаптель
                Md5Hash hash = new Md5Hash();
                password = hash.getHash(hash.getHash(param.getPass()) + userWithTheSameLogin.getSalt());

            } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
                logger.error("Can not autorize: Can't get hash of pass {]", param.getPass());
                logger.error(e);
                return SysExits.valueOf("EXIT2");
            }

            if (!userWithTheSameLogin.getLogin().equals(param.getLogin())) { //если ne совпадает логин
                logger.error("Can not autorize: {} is wrong login", param.getLogin());
                return SysExits.valueOf("EXIT1");
            } else if (!userWithTheSameLogin.getPass().equals(password)) {//если ne совпадает пароль
                logger.error("Can not autorize: Password {} is wrong for user {}", param.getPass(), param.getLogin());
                return SysExits.valueOf("EXIT2");
            }
        }
        logger.info("Authentification success for user {}", param.getLogin());
        return SysExits.valueOf("EXIT0");
    }

    public User getUserFromLogin(String login, Connection conn) throws SQLException {
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

    public boolean isLoginRegex(String login) {
        Pattern p = Pattern.compile("^[a-zA-Z0-9]+");
        Matcher m = p.matcher(login);
        return m.matches();
    }
}
