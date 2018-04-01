package com.company;

import com.company.authentific.Authentific;
import com.company.autorization.Autorization;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        //STRING PARSER
        InputStreamReader is = new InputStreamReader(System.in);
        BufferedReader bistream = new BufferedReader(is);
        String comm = null;
        try {
            comm = bistream.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] line = comm.split(" ");
        Parametrs parametrs = ParseLine.parse(line);
        //GENERAL
        if (parametrs.canAutorize() && parametrs.canAuthehtific() && parametrs.canAccaunt()) {
            Autorization.autorize(parametrs);
            Authentific.auth(parametrs);
            Accounting.account(parametrs);
        }
        else if (parametrs.canAutorize() && parametrs.canAuthehtific()){
            Autorization.autorize(parametrs);
            Authentific.auth(parametrs);
        }
        else if (parametrs.canAutorize()){
            Autorization.autorize(parametrs);
        }
    }
}
