package com.company;

import java.util.Calendar;
import java.util.Date;
import java.util.ArrayList;


public class Hotel {
    private final String name;
    private final String address;
    private final double rating;
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

    public void addRoom(Room room) {
        rooms.add(room);
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

    public Room foundAvailableRoom(RoomType roomType, Date requestCheckIn, Date requestCheckOut) {
        for (Room room : rooms) {

            if (room.getType().equals(roomType)) {
                for (int j = 0; j < room.getReservations().size(); j++) {
                    Date roomCheckIn = room.reservations.get(j).getCheckInDate();

                    Date roomCheckOut = room.reservations.get(j).getCheckOutDate();
                    if ((requestCheckOut.before(roomCheckIn)) || requestCheckIn.after(roomCheckOut)) {
                        return room;
                    }
                }
            }
        }
        return null;
    }

    public boolean isReservationPossible(RoomType roomType, ReservationRequest reservationRequest) {
        final Date requestCheckIn = reservationRequest.getCheckInDate();
        final Date requestCheckOut = reservationRequest.getCheckOutDate();
        Room room = (foundAvailableRoom(roomType, requestCheckIn, requestCheckOut));
        if (!(room == null)) {
            return true;
        }
        return false;
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

    public double calculatePrice(RoomType roomType, ReservationRequest reservationRequest) {
        return roomType.getDailyRate() * getDaysOfStay(reservationRequest.getCheckInDate(), reservationRequest.getCheckOutDate());
    }

    public void addReservation(RoomType roomType, Reservation reservation) {
        final Date reservationCheckIn = reservation.getCheckInDate();
        final Date reservationCheckOut = reservation.getCheckOutDate();
        Room room = (foundAvailableRoom(roomType, reservationCheckIn, reservationCheckOut));
        if (!(room == null)) {
            room.addReservation(roomType, reservation);
        }
        /*for (Room room : rooms) {
            if (room.getType().equals(roomType)) {
                for (int j = 0; j < room.getReservations().size(); j++) {
                    Date roomCheckIn = room.reservations.get(j).getCheckInDate();
                    System.out.println(roomCheckIn);

                    Date roomCheckOut = room.reservations.get(j).getCheckOutDate();
                    System.out.println(roomCheckOut);
                    if (( requestCheckOut.before(roomCheckIn)) || requestCheckIn.after(roomCheckOut)) {
                        room.addReservation(roomType, reservation);
                        break;
                    }
                }
            }
        }*/
    }

    @Override
    public String toString() {
        return "Welcome to the hotel " + name + ". Such a lovely place!" +
                "\nWe are located " + address +
                "\nCurrent rating is " + rating + "\n";
    }
}
