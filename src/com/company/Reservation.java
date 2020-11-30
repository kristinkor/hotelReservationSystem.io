package com.company;

import java.util.Date;


public class Reservation {
    private int id;
    private int numberOfGuests;
    private Date checkInDate, checkOutDate;

    private Guest mainGuest;


    Reservation(int id, int numberOfGuests, Date checkInDate, Date checkOutDate, Guest mainGuest) {
        this.numberOfGuests = numberOfGuests;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.id = id;
        this.mainGuest = mainGuest;
    }

    public int getNumberOfGuests() {
        return numberOfGuests;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    public int getId() {
        return id++;
    }

    public Guest getMainGuest() {
        return mainGuest;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "numberOfGuests=" + numberOfGuests +
                ", checkInDate=" + checkInDate +
                ", checkOutDate=" + checkOutDate +
                ", id=" + id +
                ", mainGuest=" + mainGuest +
                '}';
    }
}
