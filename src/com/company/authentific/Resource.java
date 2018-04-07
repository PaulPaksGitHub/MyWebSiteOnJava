package com.company.authentific;

import java.util.ArrayList;
import java.util.List;

public class Resource {
    private String adress;
    private List<String> readList = new ArrayList<String>();
    private List<String> writeList = new ArrayList<String>();
    private List<String> executeList = new ArrayList<String>();

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
        String delimeter = "\\."; // Разделитель
        readList.addAll(ReadLogins);
        writeList.addAll(WriteLogins);
        executeList.addAll(ExeLogins);
    }
}
