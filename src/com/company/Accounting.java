package com.company;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Accounting {
    public boolean account(Parametrs param) {
        return hasTrueData(param) && hasTrueVol(param);
    }

    private boolean hasTrueData(Parametrs param) {
        String ds = param.getDs();
        String de = param.getDe();

        SimpleDateFormat dts = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat dte = new SimpleDateFormat("yyyy-MM-dd");
        try {
            dts.setLenient(false);
            dts.parse(ds);
            dte.setLenient(false);
            dte.parse(de);
            return true;
        } catch (ParseException e) {
            System.exit(5);
            return false;
        }
    }

    private boolean hasTrueVol(Parametrs param) {
        try {
            int a = Integer.parseInt(param.getVol());
            return true;
        } catch (NumberFormatException e) {
            System.exit(5);
            return false;
        }
    }
}
