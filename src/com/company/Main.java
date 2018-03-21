package com.company;
public class Main {
    public static void main(String[] args) {
        Parametrs s = new Parametrs(
                "pa",
                "12",
                "B",
                "EXECUTE");
        Autorization.autorize(s);
        Authentific.auth(s);
    }
}
