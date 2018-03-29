package com.company;

import com.company.authentific.Authentific;
import com.company.autorization.Autorization;

public class Main {
    public static void main(String[] args) {
        //STRING PARSER
        String comm = "-l pa -p 12 -r C.C.C -o READ";
        String[] line = comm.split("\\ ");
        Parametrs start = ParseLine.parse(line);
        //GENERAL
        if (start.hasLogin() || start.hasPassword()) {
            Autorization.autorize(start);
            if (start.hasRes() || start.hasRole()) {
                Authentific.auth(start);
                if (start.hasDs() || start.hasDe() || start.hasVol()) {
                    Accounting.account(start);
                }
            }
        }

    }
}
