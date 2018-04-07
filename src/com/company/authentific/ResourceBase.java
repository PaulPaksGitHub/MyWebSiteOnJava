package com.company.authentific;

import com.company.parametrs.Parameters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ResourceBase {
    public boolean hasPermission(Parameters param, String cutAdress) {
        List<Resource> list = new ArrayList<>();

        String[] a = { "pa","ha"};
        List<String> readList = Arrays.asList(a);
        List<String> writeList = Arrays.asList(a);
        List<String> executeList = Arrays.asList(a);

        list.add(new Resource("B",readList, writeList, executeList ));
        list.add(new Resource("A", readList, writeList, executeList ));
        list.add(new Resource("A.B",readList, writeList, executeList ));
        list.add(new Resource("C.C.C", readList, writeList, executeList ));

        for (Resource i : list) {
            if (cutAdress.equals(i.getAdress())) {
                if (Roles.roles.READ == Roles.roles.valueOf(param.getRole()) &&
                        i.getRead().contains(param.getLogin())) {
                    return true;
                } else if (Roles.roles.WRITE == Roles.roles.valueOf(param.getRole()) &&
                        i.getWriteList().contains(param.getLogin())) {
                    return true;
                } else if (Roles.roles.EXECUTE == Roles.roles.valueOf(param.getRole()) &&
                        i.getExecuteList().contains(param.getLogin())) {
                    return true;
                }
            }
        }
        return false;
    }
}
