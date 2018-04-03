package com.company.authentific;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Resource {
    public String adress;
    public List<String> read = new ArrayList<String>();
    public List<String> write = new ArrayList<String>();
    public List<String> execute = new ArrayList<String>();

    Resource(String adres, String ReadLogins, String WriteLogins, String ExeLogins) {
        adress = adres;
        String[] subStr;
        String delimeter = "\\."; // Разделитель

        subStr = ReadLogins.split(delimeter);
        read.addAll(Arrays.asList(subStr));

        subStr = WriteLogins.split(delimeter);
        write.addAll(Arrays.asList(subStr));

        subStr = ExeLogins.split(delimeter);
        execute.addAll(Arrays.asList(subStr));
    }
}
