package com.company;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws Exception {
        Hotel h = new Hotel("California", " 424 East Palm Canyon Drive, CA 92264", 5);
        System.out.println("Welcome to the hotel " + h.name + ". Such a lovely place!");
        System.out.println("Current rating is " + h.rating + " stars!!!");

    }
}
