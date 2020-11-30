package com.company;

import java.util.Calendar;
import java.util.Date;
import java.util.ArrayList;


public class Hotel {
    private final String name;
    private final String address;
    private double rating;
    ArrayList<Room> rooms = new ArrayList<>();


    Hotel(String name, String address, double rating) {
        this.name = name;
        this.address = address;
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public String getAdress() {
        return address;
    }

    public double getRating() {
        return rating;
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    public ArrayList<RoomType> getRoomTypes() {
        ArrayList<RoomType> rt = new ArrayList<>();
        for (Room room : rooms) {
            if (!rt.contains(room.getType())) {
                rt.add(room.getType());
            }
        }
        return rt;
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public double calculatePrice(RoomType roomType, ReservationRequest reservationRequest) {
        return roomType.getDailyRate() * getDaysOfStay(reservationRequest.getCheckInDate(), reservationRequest.getCheckOutDate());
    }

    public int getDaysOfStay(Date checkIn, Date checkOut) {
        Calendar cal3 = Calendar.getInstance();
        cal3.setTime(checkIn);
        Calendar cal4 = Calendar.getInstance();
        cal4.setTime(checkOut);
        Calendar date = (Calendar) cal3.clone();
        long daysBetween = 0;
        while (date.before(cal4)) {
            date.add(Calendar.DAY_OF_MONTH, 1);
            daysBetween++;
        }
        return (int) daysBetween;
    }

    public boolean isReservationPossible(RoomType roomType, ReservationRequest reservationRequest) {
        final Date requestCheckIn = reservationRequest.getCheckInDate();
        System.out.println(requestCheckIn);
        final Date requestCheckOut = reservationRequest.getCheckOutDate();
        System.out.println(requestCheckOut);
        for (Room room : rooms) {

            if (room.getType().equals(roomType)) {

                for (int j = 0; j < room.getReservations().size(); j++) {
                    Date roomCheckIn = room.reservations.get(j).getCheckInDate();
                    System.out.println(roomCheckIn);

                    Date roomCheckOut = room.reservations.get(j).getCheckOutDate();
                    System.out.println(roomCheckOut);
                    if ((requestCheckIn.before(roomCheckIn) && requestCheckOut.before(roomCheckOut))
                            || requestCheckIn.after(roomCheckOut)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void addReservation(RoomType roomType, Reservation reservation) {

    }

    @Override
    public String toString() {
        return "Welcome to the hotel " + name + ". Such a lovely place!" +
                "\nWe are located " + address +
                "\nCurrent rating is " + rating + "\n";
    }
}
