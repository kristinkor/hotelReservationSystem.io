package com.company;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Reservation {
    private int numberOfDays;
    private Date checkInDate, checkOutDate;
    private int id;
    private Guest mainGuest;


    Reservation(int numberOfDays, Date checkInDate, Date checkOutDate, int id, Guest mainGuest){

    }

    public int getNumberOfDays() {
        return numberOfDays;
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
                "numberOfDays=" + numberOfDays +
                ", checkInDate=" + checkInDate +
                ", checkOutDate=" + checkOutDate +
                ", id=" + id +
                ", mainGuest=" + mainGuest +
                '}';
    }
}
