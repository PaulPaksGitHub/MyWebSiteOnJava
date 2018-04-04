package com.company;

import com.company.accounting.Accounting;
import com.company.authentific.Authentific;
import com.company.autorization.Autorization;
import com.company.parametrs.Parametrs;
import com.company.parametrs.ParseLine;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ParseLine defaultParser = new ParseLine();
        Parametrs user = defaultParser.parse(in.nextLine().split(" "));

        Autorization autorization = new Autorization();
        Authentific authentific = new Authentific();
        Accounting accounting = new Accounting();

        if (user.canAutorize() && user.canAuthehtific() && user.canAccaunt()) {
            autorization.isAutorized(user);
            authentific.isAuthentificated(user);
            accounting.isAccaunted(user);
        } else if (user.canAutorize() && user.canAuthehtific()) {
            autorization.isAutorized(user);
            authentific.isAuthentificated(user);
        } else if (user.canAutorize()) {
            autorization.isAutorized(user);
        }
        if (user.isH()) {
            defaultParser.printHelp(defaultParser.getOptions(), System.out);
        }
    }
}
