package com.company;

import java.util.ArrayList;

public class Hotel {
    final String name;
    private String address;
    double rating =5; // default value
    ArrayList<Room> rooms = new ArrayList<Room>();

    public Hotel(String name, String address, double rating){
        this.name = name;
        this.address = address;

    }


}
