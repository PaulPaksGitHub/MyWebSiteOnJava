package com.company.accounting;

import com.company.Parametrs;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class Accounting {
    public boolean account(Parametrs param) {
        List<AccauntedUser> list = new ArrayList<AccauntedUser>();
        if (hasTrueData(param) && hasTrueVol(param)){
            list.add(new AccauntedUser(param.getLogin(),param.getDs(),param.getDe(),param.getVol()));
            return true;
        }
        return false;
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
