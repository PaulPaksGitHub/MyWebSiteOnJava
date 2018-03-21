package com.company;

public class ResourceParser {
    public static boolean authorizeFromAdress( String fullAdress){
        String[] subStr;
        String delimeter = "\\."; // Разделитель
        subStr = fullAdress.split(delimeter);
        // Вывод результата на экран
        for(int i = 0; i < subStr.length; i++) {
            System.out.println(subStr[i]);
        }
        return false;
    }
}
