package com.company;

import java.util.ArrayList;


public class Room {
    public int number;
    RoomType type;
    ArrayList<Reservation> reservations;


    //constructor
    public Room(int number, RoomType type, ArrayList<Reservation> reservations) {
        this.number = number;
        this.type = type;
        this.reservations = reservations;
    }

    public int getNumber() {
        return number;
    }

    public RoomType getType() {
        return type;
    }

    public ArrayList<Reservation> getReservations() {
        return reservations;
    }

    public void addReservation(RoomType type, Reservation reservation) {
        this.reservations.add(reservation);
    }


    @Override
    public String toString() {
        return "Room: " +
                "room number: " + number +
                "\nType:" + type +
                "\nReservations: " + reservations;
    }


}
