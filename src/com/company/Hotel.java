package com.company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.ArrayList;


public class Hotel {
    private final String name;
    private final String address;
    private final double rating;
    ArrayList<Room> rooms = new ArrayList<>();


    public Hotel(String name, String address, double rating) {
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

    public ArrayList<Room> getRoom() {
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

    /*public Room foundAvailableRoom(Date requestCheckIn, Date requestCheckOut, ArrayList<Room> arrayRoomsByType) throws SQLException {
        Connection myConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel", "root", "password");
        try {
            Reservation reservation = new Reservation(null, 0, null, null, null);
            for (int i = 0; i < arrayRoomsByType.size(); i++) {
                String query = "select reservationId from roomReservation where number = " + arrayRoomsByType.get(i);
                PreparedStatement pr = myConnection.prepareStatement(query);
                //reservation = sqlrequest(query);
                Date roomCheckIn = reservation.getCheckInDate();
                Date roomCheckOut = reservation.getCheckOutDate();


                if ((requestCheckOut.before(roomCheckIn)) || requestCheckIn.after(roomCheckOut)) {
                    System.out.println("jiii");
                    return arrayRoomsByType.get(i);

                    //****
                }

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
*/
    /*public Room foundAvailableRoom(RoomType roomType, Date requestCheckIn, Date requestCheckOut, ArrayList<RoomReservation> roomReservations, ArrayList<Reservation> reservations) {

        for (Room room : rooms) {
            if (room.getType().equals(roomType)) {
                int j;
               if (roomReservations.size() != 0 && reservations.size() != 0){

                    for (j = 0; j < roomReservations.size(); j++) {
                        if (room.getNumber() == roomReservations.get(j).getNumber()) {
                                for (int i = 0; i < reservations.size(); i++) {
                                    if (roomReservations.get(j).getId() == reservations.get(i).getId()) {
                                        //****
                                        System.out.println(room.getNumber());
                                        Date roomCheckIn = reservations.get(i).getCheckInDate();
                                        Date roomCheckOut = reservations.get(i).getCheckOutDate();
                                        if ((requestCheckOut.before(roomCheckIn)) || requestCheckIn.after(roomCheckOut)) {
                                            System.out.println(room.getNumber());
                                            System.out.println("jiii");
                                            return room;

                                            //****
                                        }
                                        else {
                                            return null;
                                        }
                                    }
                                    else {
                                        return null;
                                    }
                                }
                            }
                        }
                    }
                System.out.println("jiii");
                return room;
            }
        }
        return null;
    }*/

/*    public boolean isReservationPossible( ReservationRequest reservationRequest, ArrayList<Room> roomsByType) throws SQLException {
        final Date requestCheckIn = reservationRequest.getCheckInDate();
        final Date requestCheckOut = reservationRequest.getCheckOutDate();
        Room room = (foundAvailableRoom(requestCheckIn, requestCheckOut, roomsByType));
        return !(room == null);
    }*/

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

    public void addReservation(RoomType roomType, Reservation reservation, ArrayList<RoomReservation> roomReservations, ArrayList<Reservation> reservations) throws SQLException {
        final Date reservationCheckIn = reservation.getCheckInDate();
        final Date reservationCheckOut = reservation.getCheckOutDate();
        Room room = (foundAvailableRoom(roomType, reservationCheckIn, reservationCheckOut));
        if (!(room == null)) {
            room.addReservation(roomType, reservation);
            connectReservationToRoom(room.getNumber(), reservation.getId());
        }
    }

    public void connectReservationToRoom(int number, String reservationId) throws SQLException {
        Connection myConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel", "root", "password");
        try {

            PreparedStatement pr = myConnection.prepareStatement("insert into "
                    + "roomReservation(reservationId, number) values(?,?)");
            pr.setString(1, reservationId);
            pr.setInt(2, number);


            pr.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            try {
                myConnection.close();
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }

        }
    }

    public Room foundAvailableRoom(RoomType roomType, Date requestCheckIn, Date requestCheckOut) {
        for (Room room : rooms) {
            if (room.getType().equals(roomType)) {
                if (room.getReservations().size() == 0) {
                    return room;
                }
                else if (isRoomAvailable(room, requestCheckIn, requestCheckOut)) {
                    return room;
                }
            }
        }
        return null;
    }

    private boolean isRoomAvailable(Room room, Date requestCheckIn, Date requestCheckOut) {
        for (int j = 0; j < room.getReservations().size(); j++) {
            Date roomCheckIn = room.reservations.get(j).getCheckInDate();
            Date roomCheckOut = room.reservations.get(j).getCheckOutDate();
            if (!((requestCheckOut.before(roomCheckIn)) || requestCheckIn.after(roomCheckOut))) {
                    return false;
            }
        }
        return true;
    }

    public boolean isReservationPossible(RoomType roomType, ReservationRequest reservationRequest) {
        final Date requestCheckIn = reservationRequest.getCheckInDate();
        final Date requestCheckOut = reservationRequest.getCheckOutDate();
        Room room = (foundAvailableRoom(roomType, requestCheckIn, requestCheckOut));
        return !(room == null);
    }

    @Override
    public String toString() {
        return "Welcome to the hotel " + name + ". Such a lovely place!" +
                "\nWe are located " + address +
                "\nCurrent rating is " + rating + "\n";
    }
}
