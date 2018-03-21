package com.company;

import java.util.Arrays;
import java.util.List;

public class Authentific {
    public static List<String> ROLES = Arrays.asList("READ", "WRITE", "EXECUTE");
    public static boolean auth(Parametrs param){
        System.out.println("Authetification");
        if (ROLES.contains(param.role)){
            if (ResourceParser.authorizeFromAdress(param)){
                System.out.println("Authetificated");
                return true;
            } else {
                System.exit(6);
                return false;
            }
        } else {
            System.exit(3);
            return false;
        }
    }
}
