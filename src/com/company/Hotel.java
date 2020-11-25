package com.company;

import java.util.ArrayList;
import java.util.Date;

public class Hotel {
    private String name;
    private String address;
    private double rating = 5; // default value
    ArrayList<Room> rooms = new ArrayList<>();

    Hotel(String name, String address, double rating){
        this.name = name;
        this.address = address;
    }

    public String getName(){
        return name;
    }

    public String getAdress(){
        return address;
    }

    public double getRating(){
        return rating;
    }

    public void addRoom(Room room){
        rooms.add(room);

    }

    public int getDaysOfStay(Date checkIn, Date checkOut){
        return (int)( (checkOut.getTime() - checkIn.getTime()) / (1000 * 60 * 60 * 24));
    }

    public double calculatePrice(RoomType roomType, ReservationRequest reservationRequest){
        return roomType.getDailyRate() * getDaysOfStay(reservationRequest.getCheckInDate(), reservationRequest.getCheckOutDate());
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", rating=" + rating +
                ", rooms=" + rooms +
                '}';
    }
}
