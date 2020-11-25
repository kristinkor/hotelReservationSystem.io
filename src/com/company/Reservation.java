package com.company;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Reservation {
    private int numberOfGuests;
    private Date checkInDate, checkOutDate;
    private int id;
    private Guest mainGuest;


    Reservation(int numberOfGuests, Date checkInDate, Date checkOutDate, int id, Guest mainGuest){

    }

    public int getNumberOfGuests() {
        return numberOfGuests;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public Date getCheckOutDate(){
        return checkOutDate;
    }

    public int getId() {
        return id;
    }

    public Guest getMainGuest() {
        return mainGuest;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "numberOfDays=" + numberOfGuests +
                ", checkInDate=" + checkInDate +
                ", checkOutDate=" + checkOutDate +
                ", id=" + id +
                ", mainGuest=" + mainGuest +
                '}';
    }
}
