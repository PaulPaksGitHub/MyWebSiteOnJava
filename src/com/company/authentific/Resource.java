package com.company.authentific;

import java.util.ArrayList;
import java.util.List;

public class Resource {
    private String adress;
    private List<String> readList = new ArrayList<>();
    private List<String> writeList = new ArrayList<>();
    private List<String> executeList = new ArrayList<>();

    public String getAdress() {
        return adress;
    }

    public List<String> getRead() {
        return readList;
    }

    public List<String> getWriteList() {
        return writeList;
    }

    public List<String> getExecuteList() {
        return executeList;
    }

    Resource(String adress, List<String> ReadLogins, List<String> WriteLogins, List<String> ExeLogins) {
        this.adress = adress;
        this.readList.addAll(ReadLogins);
        this.writeList.addAll(WriteLogins);
        this.executeList.addAll(ExeLogins);
    }
}
