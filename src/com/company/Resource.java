package com.company;

import java.util.ArrayList;
import java.util.List;

public class Resource {
    public String adress;
    public List<String> READ = new ArrayList<String>() ;
    public List<String> WRITE = new ArrayList<String>() ;
    public List<String> EXECUTE = new ArrayList<String>() ;
    Resource (String adres,String ReadLogins, String WriteLogins, String ExeLogins){
        adress = adres;
        String[] subStr;
        String delimeter = "\\."; // Разделитель

        subStr = ReadLogins.split(delimeter);
        for(int i = 0; i < subStr.length; i++) {
            READ.add(subStr[i]); }

        subStr = WriteLogins.split(delimeter);
        for(int i = 0; i < subStr.length; i++) {
            WRITE.add(subStr[i]); }

        subStr = ExeLogins.split(delimeter);
        for(int i = 0; i < subStr.length; i++) {
            EXECUTE.add(subStr[i]); }
    }
}
