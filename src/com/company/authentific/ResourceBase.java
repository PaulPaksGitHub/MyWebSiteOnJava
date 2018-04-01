package com.company.authentific;

import com.company.Parametrs;

import java.util.ArrayList;
import java.util.List;

public class ResourceBase {
    private enum roles {READ, WRITE, EXECUTE}

    public boolean hasPermission(Parametrs param, String cutAdress) {
        //BASE INIT
        List<Resource> list = new ArrayList<Resource>();
        list.add(new Resource("B", "ha", "", "pa"));
        list.add(new Resource("A", "pa.ha", "", ""));
        list.add(new Resource("A.B", "pa", "", ""));
        list.add(new Resource("C.C.C", "pa", "pa", "pa"));

        for (Resource i : list) {
            //System.out.println(i.adress+" "+cutAdress);
            if (cutAdress.equals(i.adress)) {
                if (roles.READ == roles.valueOf(param.role) && i.read.contains(param.login)) {
                    return true;
                } else if (roles.WRITE == roles.valueOf(param.role) && i.write.contains(param.login)) {
                    return true;
                } else if (roles.EXECUTE == roles.valueOf(param.role) && i.execute.contains(param.login)) {
                    return true;
                }
            }
        }
        return false;
    }
}
