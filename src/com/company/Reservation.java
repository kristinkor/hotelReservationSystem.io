package com.company;

import java.util.Date;


public class Reservation {
    private String id;
    private int numberOfGuests;
    private Date checkInDate, checkOutDate;
    private Guest mainGuest;

    Reservation(String id, int numberOfGuests, Date checkInDate, Date checkOutDate, Guest mainGuest) {
        this.numberOfGuests = numberOfGuests;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.id = id;
        this.mainGuest = mainGuest;
    }

    public String getId() {
        return id;
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

    public Guest getMainGuest() {
        return mainGuest;
    }

    @Override
    public String toString() {
        return "Reservation: " +
                "numberOfGuests " + numberOfGuests +
                ", checkInDate " + checkInDate +
                ", checkOutDate " + checkOutDate +
                ", id " + id +
                ", mainGuest " + mainGuest;
    }
}
