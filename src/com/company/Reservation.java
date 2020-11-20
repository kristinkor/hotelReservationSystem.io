package com.company;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Reservation {
    private String id;
    private Date checkIn, checkOut;
    private int daysOfStay;
    private Guest guest;
    private boolean breakfast;
    public double roomPrice;

    public Reservation(Room room, int startDate, int endDate, Guest guest){
        //populate the member variables.
    }


    public int getDaysOfStay(Date checkIn, Date checkOut){
        return (int)( (checkOut.getTime() - checkIn.getTime()) / (1000 * 60 * 60 * 24));
    }

    public double calculateTotalPrice(){
        return roomPrice * daysOfStay;
    }
}
