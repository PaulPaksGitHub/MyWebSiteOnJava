package com.company;

import java.util.Arrays;
import java.util.List;

public class Authentific {
    public static List<String> ROLES = Arrays.asList("READ", "WRITE", "EXECUTE");
    public static boolean auth(Parametrs param){
        System.out.println("Auth");
        if (ROLES.contains(param.role)){
            System.out.println("TRUE");//If role in ROLES, start parcing resources
            ResourceParser.authorizeFromAdress(param.res);
        } else {
            System.exit(3);
        }
        return false;
    }
}
