package com.company;

import java.util.Arrays;
import java.util.List;

public class Authentific {
    public static List<String> ROLES = Arrays.asList("READ", "WRITE", "EXECUTE");
    public static boolean auth(Parametrs param){
        if (ROLES.contains(param.role)){
            if (ResourceParser.authorizeFromAdress(param)){
                return true;
            } else {
                System.exit(4);
                return false;
            }
        } else {
            System.exit(3);
            return false;
        }
    }
}
