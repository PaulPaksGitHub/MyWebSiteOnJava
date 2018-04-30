package com.company.authentific;

import com.company.parametrs.Parameters;

import java.sql.*;

public class ResourceParser {
    //из полного адреса ресурса создает частичный (начиная с родительского)
    //  и по получившемуся адресу проводит аутентификацию.
    //с каждой итерацией добавляет к частичному адресу следующий узел из полного адреса
    // и повторяет попыткум аутентификации.
    public boolean authorizeFromAdress(Parameters param) {
        String fullAdress = param.getRes();
        String[] subStr;
        String delimeter = "\\.";
        subStr = fullAdress.split(delimeter);
        String newStr = "";
        for (String aSubStr : subStr) {
            newStr += aSubStr;
            if (hasPermission(param, newStr)) {
                return true;
            }
            newStr += ".";
        }
        return false;
    }

    public boolean hasPermission(Parameters param, String cutAdress) {
        try(Connection conn = DriverManager.
                getConnection("jdbc:h2:./data/db", "sa", "");) {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select * from res where adress = '"+cutAdress+"'");
            while (rs.next()) {
                if (rs.getString("login").equals(param.getLogin()) &&
                        rs.getString("role").equals(param.getRole())){
                    return true;
                }
            }
            return false;

        } catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return false;
    }
}
