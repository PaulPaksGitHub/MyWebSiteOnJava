package com.company;
public class Main {
    public static void main(String[] args) {
        Parametrs s = new Parametrs(
                "pa",
                "12",
                "A.B",
                "READ");
        Autorization.autorize(s);
        Authentific.auth(s);
    }
}
