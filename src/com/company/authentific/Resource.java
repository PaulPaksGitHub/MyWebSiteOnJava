package com.company.authentific;

import java.util.ArrayList;
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
        for (int i = 0; i < subStr.length; i++) {
            read.add(subStr[i]);
        }

        subStr = WriteLogins.split(delimeter);
        for (int i = 0; i < subStr.length; i++) {
            write.add(subStr[i]);
        }

        subStr = ExeLogins.split(delimeter);
        for (int i = 0; i < subStr.length; i++) {
            execute.add(subStr[i]);
        }
    }
}
