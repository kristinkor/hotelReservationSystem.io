package com.company;

import java.util.Date;


public class ReservationRequest {
    private Date checkInDate, checkOutDate;
    private int numberOfGuests;

    ReservationRequest(Date checkInDate, Date checkOutDate, int numberOfGuests) {
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.numberOfGuests = numberOfGuests;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    public int getNumberOfGuests() {
        return numberOfGuests;
    }

    @Override
    public String toString() {
        return "Your Reservation Request: " +
                "\nThe date of check-in: " + checkInDate +
                "\nThe date of check-out: " + checkOutDate +
                "\nNumber Of Guests " + numberOfGuests;
    }
}
