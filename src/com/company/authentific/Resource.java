package com.company.authentific;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Resource {
    private String adress;
    private List<String> read = new ArrayList<String>();
    private List<String> write = new ArrayList<String>();
    private List<String> execute = new ArrayList<String>();

    public String getAdress() {
        return adress;
    }

    public List<String> getRead() {
        return read;
    }

    public List<String> getWrite() {
        return write;
    }

    public List<String> getExecute() {
        return execute;
    }

    Resource(String adress, String ReadLogins, String WriteLogins, String ExeLogins) {
        this.adress = adress;
        String delimeter = "\\."; // Разделитель
        read.addAll(Arrays.asList(ReadLogins.split(delimeter)));
        write.addAll(Arrays.asList(WriteLogins.split(delimeter)));
        execute.addAll(Arrays.asList(ExeLogins.split(delimeter)));
    }
}
