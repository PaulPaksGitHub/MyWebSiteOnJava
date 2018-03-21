package com.company;

import java.util.ArrayList;
import java.util.List;

public class ResourceBase {
    public static boolean hasPermission(Parametrs param, String cutAdress){
        List<Resource> list = new ArrayList<Resource>();
        list.add(new Resource("B"));
        list.add(new Resource("A"));
        list.add(new Resource("A.B"));
        for (Resource i : list){
            //System.out.println(i.adress+cutAdress);
            if (cutAdress.equals(i.adress)){
                return true;

            }
        }
        return false;
    }
}
