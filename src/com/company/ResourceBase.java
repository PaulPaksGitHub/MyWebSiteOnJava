package com.company;

import java.util.ArrayList;
import java.util.List;

public class ResourceBase {
    public static boolean hasPermission(Parametrs param, String cutAdress){
        //BASE INIT
        List<Resource> list = new ArrayList<Resource>();
        list.add(new Resource("B", "ha","","pa"));
        list.add(new Resource("A", "pa.ha","",""));
        list.add(new Resource("A.B", "pa","",""));
        list.add(new Resource("C.C.C", "pa","pa","pa"));

        for (Resource i : list){
            //System.out.println(i.adress+" "+cutAdress);
            if (cutAdress.equals(i.adress)){
                if (param.role.equals("READ") && i.READ.contains(param.login)){
                    return true;
                } else if (param.role.equals("WRITE") && i.WRITE.contains(param.login)) {
                    return true;
                } else if (param.role.equals("EXECUTE") && i.EXECUTE.contains(param.login)) {
                    return true;
                }
            }
        }
        return false;
    }
}
