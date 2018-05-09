package main.java.com.company.authentification;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Hash {
    public String getHash(String str) throws NoSuchAlgorithmException,
            UnsupportedEncodingException {
        MessageDigest m = MessageDigest.getInstance("MD5");
        m.reset();
        // передаем в MessageDigest байт-код строки
        m.update(str.getBytes("utf-8"));
        // получаем MD5-хеш строки без лидирующих нулей
        String s2 = new BigInteger(1, m.digest()).toString(16);
        StringBuilder sb = new StringBuilder(32);
        // дополняем нулями до 32 символов, в случае необходимости
        for (int i = 0, count = 32 - s2.length(); i < count; i++) {
            sb.append("0");
        }
        return sb.append(s2).toString();
    }
}