package com.company;

import java.util.Date;

public class Room {
    String roomType;
    public int roomID;
    public double price;

    boolean isAvailable(Date checkIn, int numberOfDays){
        return true;
    }

}
