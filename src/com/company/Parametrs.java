package com.company;

public class Parametrs {
    //ARGUMENTS
    public static String login="";
    public static String pass="";
    public static String res="";
    public static String role="";

    //METHODS
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
    Parametrs(String log, String pas, String resource, String rol) {
        login = log;
        pass = pas;
        res = resource;
        role = rol;
    }
}
