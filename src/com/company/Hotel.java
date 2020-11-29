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

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    public ArrayList<RoomType> getRoomTypes(){

        ArrayList<RoomType> roomType = null;
        for (int i = 0; i < rooms.size(); i++) {
            roomType.add(rooms.get(i).getType());
        }
        return roomType;
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

    public boolean isReservationPossible(RoomType roomType, ReservationRequest resRequest) {
        final Date early = resRequest.getCheckInDate();
        final Date late = resRequest.getCheckOutDate();
        /*for(int i = 0; i < reservation.size(),) {
            //if(!(early.isAfter(reservation.getCheckInDate()) || late.isBefore(reservation.getCheckInDate()))
            return true;
        }*/
        return false;
    }

    public void addReservation(RoomType roomType, Reservation reservation){

    }

    @Override
    public String toString() {
        return "Welcome to the hotel " + name + ". Such a lovely place!" +
                "\nWe are located " + address +
                "\nCurrent rating is " + rating +
                "\nRooms: " + rooms;
    }
}
