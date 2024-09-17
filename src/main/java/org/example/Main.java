package org.example;

import java.sql.Timestamp;
import java.util.Date;

public class Main {
    public static void main(String[] args) {

        Date date = new Date();
        System.out.println(date.getTime());
        Date date2 = new Date(date.getTime());
        System.out.println(date2);
    }
}