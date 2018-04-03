package com.company;

import com.company.accounting.Accounting;
import com.company.authentific.Authentific;
import com.company.autorization.Autorization;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ParseLine defaultParser = new ParseLine();
        Parametrs parametrs = defaultParser.parse(in.nextLine().split(" "));

        Autorization autorization = new Autorization();
        Authentific authentific = new Authentific();
        Accounting accounting = new Accounting();

        if (parametrs.canAutorize() && parametrs.canAuthehtific() && parametrs.canAccaunt()) {
            autorization.autorize(parametrs);
            authentific.authentific(parametrs);
            accounting.account(parametrs);
        } else if (parametrs.canAutorize() && parametrs.canAuthehtific()) {
            autorization.autorize(parametrs);
            authentific.authentific(parametrs);
        } else if (parametrs.canAutorize()) {
            autorization.autorize(parametrs);
        }
    }
}
