package com.company.authentific;

import com.company.Parametrs;

public class ResourceParser {
    public boolean authorizeFromAdress(Parametrs param) {
        String fullAdress = param.getRes();
        String[] subStr;
        String delimeter = "\\.";
        subStr = fullAdress.split(delimeter);
        String newStr = "";
        for (String aSubStr : subStr) {
            newStr += aSubStr;
            ResourceBase user = new ResourceBase();
            if (user.hasPermission(param, newStr)) {
                return true;
            }
            newStr += ".";
        }
        return false;
    }
}
