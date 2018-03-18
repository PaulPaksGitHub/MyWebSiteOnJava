package com.company;
import java.util.ArrayList;
import java.util.List;
public class Main {
    public static void main(String[] args) {
        List<User> list = new ArrayList<User>();
        list.add(new User ("pa","12"));
        list.add(new User ("ha","34"));
	    System.out.println(list.get(0).pass);
    }
}
