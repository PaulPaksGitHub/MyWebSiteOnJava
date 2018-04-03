package com.company.accounting;

import com.company.Parametrs;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class Accounting {
    public boolean account(Parametrs param) {
        List<AccauntedUser> list = new ArrayList<AccauntedUser>();
        if (hasTrueData(param) && hasTrueVol(param)) {
            list.add(new AccauntedUser(param.getLogin(), param.getDs(), param.getDe(), param.getVol()));
            return true;
        }
        return false;
    }

    private boolean hasTrueData(Parametrs param) {
        String ds = param.getDs();
        String de = param.getDe();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            LocalDate.parse(ds, formatter);
            LocalDate.parse(de, formatter);
            return true;
        } catch (DateTimeParseException e) {
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
