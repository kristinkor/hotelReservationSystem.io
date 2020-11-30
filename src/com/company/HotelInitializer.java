package com.company;

import java.util.*;
import java.util.ArrayList;


public class HotelInitializer {
    // Initialize hotel
    Hotel h = new Hotel("California", " 424 East Palm Canyon Drive, CA 92264", 5);

    // Initialise the list of Room types


    public ArrayList<RoomType> roomTypeInit() {
        ArrayList <RoomType> roomTypes = new ArrayList();

        // Initialise the list of facilities for Superior King Room room type
        ArrayList<String> facilitiesKingRoom = new ArrayList<>();
        facilitiesKingRoom.add("Air conditioning");
        facilitiesKingRoom.add("Attached bathroom");
        facilitiesKingRoom.add("Flat-screen TV");

        // Initialise the list of facilities for Superior King Room room type
        ArrayList<String> facilitiesKingSuite = new ArrayList<>();
        facilitiesKingSuite.add("291 square feet");
        facilitiesKingSuite.add("Attached bathroom");
        facilitiesKingSuite.add("Pool view");

        // Initialise the list of facilities for Superior Queen Room room type
        ArrayList<String> facilitiesQueenRoom = new ArrayList<>();
        facilitiesQueenRoom.add("Attached bathroom");
        facilitiesQueenRoom.add("Pool view");


        // Initialise the list of facilities for Queen Room with Two Queen Beds room type
        ArrayList<String> facilitiesQueenDouble = new ArrayList<>();
        facilitiesQueenDouble.add("2 queen beds");

        //Initialise the list of facilities for Studio with Pool View
        ArrayList<String> facilitiesStudio = new ArrayList<>();
        facilitiesQueenRoom.add("Attached bathroom");
        facilitiesStudio.add("Pool view");

        roomTypes.add(new RoomType(1111, "Superior King Room", 197, 2, facilitiesKingRoom));
        roomTypes.add(new RoomType(1112, "Superior King Suite", 239, 2, facilitiesKingSuite));
        roomTypes.add(new RoomType(1113, "Superior Queen Room", 174, 2, facilitiesQueenRoom));
        roomTypes.add(new RoomType(1114, "Queen Room with Two Queen Beds", 181, 4, facilitiesQueenDouble));
        roomTypes.add(new RoomType(1115, "Studio with Pool View", 164, 2, facilitiesStudio));

        return roomTypes;
    }

    public void roomInit(ArrayList<RoomType> roomTypes) {
        Date checkIn = new java.util.Date("Wed Dec 12 00:00:00 EST 2020");
        Date checkOut = new java.util.Date("Wed Dec 20 00:00:00 EST 2020");
        Guest g = new Guest("5", "a", "b");


        Reservation reservation = new Reservation(1, 2,checkIn , checkOut,  g);
        ArrayList<Reservation> r = new ArrayList<>();
        r.add(reservation);

        h.addRoom(new Room(111, roomTypes.get(0), null));
        h.addRoom(new Room(112, roomTypes.get(0), null));
        h.addRoom(new Room(113, roomTypes.get(4), null));
        h.addRoom(new Room(114, roomTypes.get(4), null));
        h.addRoom(new Room(115, roomTypes.get(4), null));
        h.addRoom(new Room(116, roomTypes.get(0), null));
        h.addRoom(new Room(118, roomTypes.get(1), r));
        h.addRoom(new Room(119, roomTypes.get(2), null));
        h.addRoom(new Room(120, roomTypes.get(3), null));

    }
}
