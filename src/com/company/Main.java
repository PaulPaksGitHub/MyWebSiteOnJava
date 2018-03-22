package com.company;

public class Main {
    public static void main(String[] args) {
        //STRING PARSER
        String comm = "-o EXECUTE -l pa -p 12 -r B -s 2012-12-12 -e 2018-12-12 -v 3 -h";
        String[] line = comm.split("\\ ");
        Parametrs start=ParseLine.parse(line);
        //GENERAL
        Autorization.autorize(start);
        Authentific.auth(start);
        Accounting.account(start);
    }
}
