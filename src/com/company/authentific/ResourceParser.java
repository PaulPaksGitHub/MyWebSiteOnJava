package com.company.authentific;

import com.company.Parametrs;

public class ResourceParser {
    public static boolean authorizeFromAdress(Parametrs param) {
        String fullAdress = param.res;
        String[] subStr;
        String delimeter = "\\."; // Разделитель
        subStr = fullAdress.split(delimeter);
        String newStr = "";
        for (int i = 0; i < subStr.length; i++) {
            newStr += subStr[i];
            if (ResourceBase.hasPermission(param, newStr)) {
                return true;
            }
            newStr += ".";
        }
        return false;
    }
}
