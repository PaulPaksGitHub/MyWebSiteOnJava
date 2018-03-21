package com.company;

public class Accounting {
    public static boolean account(Parametrs param){
        String ds = param.ds;
        String de = param.de;
        String[] subStr;
        String[] subStr1;
        String delimeter = "\\-"; // Разделитель
        subStr = ds.split(delimeter);
        subStr1 = de.split(delimeter);
        if (subStr.length == subStr1.length){
            for(int i = 0; i < subStr.length; i++) {
                int dsNum = Integer.parseInt(subStr[i]);
                int deNum = Integer.parseInt(subStr1[i]);
                if (deNum < dsNum){
                    System.exit(5);
                    return false;
                }
            }
        } else {
            System.exit(5);
            return false;
        }
        try {
            int a=Integer.parseInt(param.vol);
            return true;
        }
        catch (NumberFormatException e) {
            System.exit(5);
            return false;
        }
    }
}
