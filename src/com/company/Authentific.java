package com.company;

import java.util.Arrays;
import java.util.List;

public class Authentific {
    //private static String ROLES;
    public static List<String> ROLES = Arrays.asList("READ", "WRITE", "EXECUTE");
    public static boolean auth(String res, String rol){
        if (ROLES.contains(rol)){
            //If role in ROLES, start parcing resources
        }
        return false;
    }
}
