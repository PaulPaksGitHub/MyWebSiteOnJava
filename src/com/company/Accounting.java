package com.company;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Accounting {
    public static boolean account(Parametrs param){
        String ds = param.ds;
        String de = param.de;

        SimpleDateFormat dts = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat dte = new SimpleDateFormat("yyyy-MM-dd");
        try {
            dts.setLenient(false);
            dts.parse(ds);
            dte.setLenient(false);
            dte.parse(de);
        } catch (ParseException e) {
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
