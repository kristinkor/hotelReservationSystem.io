package com.company;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Guest {
    private String name;
    private String surname;
    private int id;

    Guest(String name, String surname, int id){
        this.name = name;
        this.surname = surname;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Guest{" +
                "surname='" + surname + '\'' +
                '}';
    }
}
