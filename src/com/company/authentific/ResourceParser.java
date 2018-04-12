package com.company.authentific;

import com.company.parametrs.Parameters;

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
        ResourceBase user = new ResourceBase();
        for (String aSubStr : subStr) {
            newStr += aSubStr;
            if (user.hasPermission(param, newStr)) {
                return true;
            }
            newStr += ".";
        }
        return false;
    }
}
