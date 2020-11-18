package com.company;

public class Hotel {
    final String hotelName;
    final int numberOfRooms;
    private String address;
    double rating = 5; // default value

    public Hotel(String name, int num, String address){
        this.hotelName = name;
        this.numberOfRooms = num;
        this.address = address;
    }

    public double currentRating(){
        return rating;
    }

}
