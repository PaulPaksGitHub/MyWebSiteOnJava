package com.company;

public class Parametrs {
    //ARGUMENTS
    public static String login = "";
    public static String pass = "";
    public static String res = "";
    public static String role = "";
    public static String ds = "";
    public static String de = "";
    public static String vol = "";

    //METHODS
    public static boolean is_Empty() {
        if (login == "" && pass == "")
            return true;
        else return false;
    }

    public static boolean hasLogin() {
        if (login != "") return true;
        else return false;
    }

    public static boolean hasRes() {
        if (res != "") return true;
        else return false;
    }

    public static boolean hasRole() {
        if (role != "") return true;
        else return false;
    }

    public static boolean hasDs() {
        if (ds != "") return true;
        else return false;
    }

    public static boolean hasDe() {
        if (de != "") return true;
        else return false;
    }

    public static boolean hasVol() {
        if (vol != "") return true;
        else return false;
    }

    public static boolean hasPassword() {
        if (pass != "") return true;
        else return false;
    }

    //CREATING
    Parametrs(String log, String pas, String resource, String rol, String dts, String dte, String vl) {
        login = log;
        pass = pas;
        res = resource;
        role = rol;
        ds = dts;
        de = dte;
        vol = vl;
    }
}
