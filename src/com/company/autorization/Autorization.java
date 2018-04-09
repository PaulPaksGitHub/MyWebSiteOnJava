package com.company.autorization;

import com.company.parametrs.Parameters;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Autorization {
    public void isAutorized(Parameters param) {
        if (!param.hasLogin() && !param.hasPassword()) { //если логина и пароля нет нет, то работа программы завершается
            System.exit(6);

        } else if (param.hasLogin() && param.hasPassword()) {//введениы и логин, и пароль, то пытаемся авторизировать
            if (!isLoginRegex(param.getLogin())) {//если пароль не соответствует шаблону
                System.exit(1);
                return;
            }
            UserBase userBibl = new UserBase();
            User userWithTheSameLogin = userBibl.getUserFromLogin(param);
            String password;

            try { //получаем хэш пароля, который ввел пользоаптель
                Md5Hash hash = new Md5Hash();
                password = hash.getHash(hash.getHash(param.getPass()) + userWithTheSameLogin.getSalt());
            } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
                System.exit(2);
                return;
            }

            if (!userWithTheSameLogin.getLogin().equals(param.getLogin())) { //если ne совпадает логин
                System.exit(1);
            } else if (!userWithTheSameLogin.getPass().equals(password)) {//если ne совпадает пароль
                System.exit(2);
            }
        } else if (!param.hasLogin()) {//если отсутствует логин
            System.exit(1);
        } else if (!param.hasPassword()) {//если отсутствует пароль
            System.exit(2);
        }
    }

    private boolean isLoginRegex(String login) {
        Pattern p = Pattern.compile("^[a-z0-9]+");
        Matcher m = p.matcher(login);
        return m.matches();
    }
}
