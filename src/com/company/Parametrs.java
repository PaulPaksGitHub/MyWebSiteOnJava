package com.company;

public class Parametrs {
    public static String login="";
    public static String pass="";

    public static boolean is_Empty(){
        if (login=="" && pass=="")
            return true;
        else return false;
    }
    public static boolean hasLogin(){
        if (login!="") return true;
        else return false;
    }
    public static boolean hasPassword(){
        if (pass!="") return true;
        else return false;
    }
    Parametrs(String log, String pas) {
        login = log;
        pass = pas;
    }
}
