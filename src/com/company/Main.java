package com.company;
public class Main {
    public static void main(String[] args) {
        Parametrs start = new Parametrs(
                "pa",
                "12",
                "B",
                "EXECUTE",
                "2012-12-12",
                "2018-12-12",
                "0");
        Autorization.autorize(start);
        Authentific.auth(start);
        Accounting.account(start);
    }
}
