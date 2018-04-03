package com.company;

import com.company.authentific.Authentific;
import com.company.autorization.Autorization;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        InputStreamReader is = new InputStreamReader(System.in);
        BufferedReader bistream = new BufferedReader(is);
        Parametrs parametrs = null;
        try {
            String[] line = bistream.readLine().split(" ");
            parametrs = ParseLine.parse(line);
        } catch (IOException e) {
            System.exit(6);
        }
        Autorization doAutorization = new Autorization();
        Authentific doAuthentific = new Authentific();
        Accounting doAccounting = new Accounting();

        if (parametrs.canAutorize() && parametrs.canAuthehtific() && parametrs.canAccaunt()) {
            doAutorization.autorize(parametrs);
            doAuthentific.auth(parametrs);
            doAccounting.account(parametrs);
        } else if (parametrs.canAutorize() && parametrs.canAuthehtific()) {
            doAutorization.autorize(parametrs);
            doAuthentific.auth(parametrs);
        } else if (parametrs.canAutorize()) {
            doAutorization.autorize(parametrs);
        }
    }
}
