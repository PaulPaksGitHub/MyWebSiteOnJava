package com.company.authentific;

import com.company.parametrs.Parameters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ResourceBase {
    private List<Resource> resourceList = new ArrayList<>();

    public boolean hasPermission(Parameters param, String cutAdress) {

        for (Resource i : resourceList) {
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

    ResourceBase() {
        this.resourceList.add(new Resource("B", Arrays.asList("pa", "ha"), Arrays.asList("ha"), Arrays.asList("ha")));
        this.resourceList.add(new Resource("A", Arrays.asList("pa"), Arrays.asList("pa"), Arrays.asList("")));
        this.resourceList.add(new Resource("A.B", Arrays.asList("ha"), Arrays.asList(""), Arrays.asList("pa")));
        this.resourceList.add(new Resource("C.C.C", Arrays.asList("pa"), Arrays.asList("ha"), Arrays.asList("")));
    }
}
