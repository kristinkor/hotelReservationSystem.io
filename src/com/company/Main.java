package com.company;

public class Main {
    public static void main(String[] args) throws Exception {
        Hotel h = new Hotel("California", 10, " 424 East Palm Canyon Drive, CA 92264");
        System.out.println("Welcome to the hotel " + h.hotelName + ". Such a lovely place!");
        System.out.println("Current rating is " + h.currentRating() + " stars!!!");



        Search s = new Search();
        s.getCheckInDate();
        s.getCheckOutDate();

    }
}
