package com.company.authentific;

import com.company.parametrs.Parameters;

public class ResourceParser {
    //
    public boolean authorizeFromAdress(Parameters param) {
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
