package com.company;

import java.util.ArrayList;
import java.util.Date;

public class Room {
    public int number;
    public double price;
    //RoomType type = new RoomType();
    ArrayList<Reservation> reservations = new ArrayList<>();
    //constructor
    Room (int number, RoomType type, ArrayList<Reservation> reservations){

    }

    public int getNumber() {
        return number;
    }

    boolean isAvailable(Date checkIn, int numberOfDays){
        return true;
    }

}
