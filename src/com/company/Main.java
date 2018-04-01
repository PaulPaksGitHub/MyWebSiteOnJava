package com.company;

import com.company.authentific.Authentific;
import com.company.autorization.Autorization;

public class Main {
    public static void main(String[] args) {
        //STRING PARSER
        //String comm = "-l pa -p 12 -r C.C.C -o READ";
        String comm = args[0];
        String[] line = comm.split(" ");
        Parametrs parametrs = ParseLine.parse(line);
        //GENERAL
        if (parametrs.canAutorize()) {
            Autorization.autorize(parametrs);
            if (parametrs.canAuthehtific()) {
                Authentific.auth(parametrs);
                if (parametrs.canAccaunt()) {
                    Accounting.account(parametrs);
                }
            }
        }
    }
}
