package com.company;

public class RoomReservation {
    private String id;
    private int number;

    public RoomReservation(String id, int number) {
        this.id = id;
        this.number = number;
    }

    public String getId() {
        return id;
    }

    public int getNumber() {
        return number;
    }
}
