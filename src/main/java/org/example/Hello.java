package org.example;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Hello {
    public static void main(String[] args) {
        String name = "";
        System.out.println("Hello World!");
        System.out.println("Name " + name);
        long millis = System.currentTimeMillis();
//        Date date = new Date(millis);
        ZonedDateTime date =ZonedDateTime.now(ZoneId.of("Europe/Moscow"));
        System.out.println("Time " + date);
        System.exit(1);
    }
}
