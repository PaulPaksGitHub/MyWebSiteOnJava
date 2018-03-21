package com.company;

public class ResourceParser {
    public static boolean authorizeFromAdress( Parametrs param){
        String fullAdress = param.res;
        String[] subStr;
        String delimeter = "\\."; // Разделитель
        subStr = fullAdress.split(delimeter);
        // Вывод результата на экран
        for(int i = 0; i < subStr.length; i++) {
            if(ResourceBase.hasPermission(param, subStr[i])){
                return true;
            }
            System.out.println(subStr[i]);
        }
        return false;
    }
}
