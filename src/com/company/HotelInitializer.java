package com.company;

import java.sql.*;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


import java.sql.Connection;


public class HotelInitializer {
    // Initialize hotel
    Hotel h = new Hotel("California", " 424 East Palm Canyon Drive, CA 92264", 5);
    ArrayList<RoomType> roomTypes = new ArrayList<>();

    public ArrayList<RoomType> roomTypeInit() {
        try {
            Connection myConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel", "root", "password");
            Statement myStatement = myConnection.createStatement();
            ResultSet myResultStatement = myStatement.executeQuery("select * from hotel.`roomType`");


            while (myResultStatement.next()) {
                ArrayList<String> facilities = new ArrayList<>();
                facilities.add("Beautiful view");
                facilities.add("Jacuzzi");
                roomTypes.add(new RoomType(myResultStatement.getInt("id"), myResultStatement.getString("name"),
                        myResultStatement.getDouble("dailyRate"), myResultStatement.getInt("capacity"), facilities));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return roomTypes;
    }

    public int saveReservations(Reservation reservation, Guest guest) throws SQLException {
        Connection myConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel", "root", "password");

        try {

            PreparedStatement pr = myConnection.prepareStatement("insert into "
                    + "reservation(id, numberOfGuests, checkInDate, checkOutDate, guestId, name, surname) values(?,?,?,?,?,?,?)");
            pr.setString(1, reservation.getId());

            pr.setInt(2, reservation.getNumberOfGuests());
            Format formatter = new SimpleDateFormat("MM.dd.yyyy");
            String checkIn = formatter.format(reservation.getCheckInDate());
            Format formatter1 = new SimpleDateFormat("MM.dd.yyyy");
            String checkOut = formatter1.format(reservation.getCheckOutDate());
            pr.setString(3, checkIn);
            pr.setString(4, checkOut);
            pr.setString(5, guest.getId());
            pr.setString(6, guest.getName());
            pr.setString(7, guest.getSurname());
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
            return 0;

        }
        try {
            myConnection.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return 1;
    }

/*    public ArrayList<Room> roomInit(ArrayList<RoomType> rt, int type) {
        ArrayList<Room> rooms = new ArrayList<>();
        try {

            Connection myConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel", "root", "password");
            Statement myStatement = myConnection.createStatement();
            //    ResultSet myResultStatement = myStatement.executeQuery("select * from hotel.`room`");
            String query = "select * from hotel.`room` where roomTypeId = " + type;
            ResultSet myResultStatement = myStatement.executeQuery(query);

*//*            Date checkIn = new java.util.Date("Wed Dec 20 00:00:00 EST 2020");
            Date checkOut = new java.util.Date("Wed Dec 25 00:00:00 EST 2020");
            Guest g = new Guest("5", "a", "b");

            Reservation reservation = new Reservation("1", 2, checkIn, checkOut, g);
            ArrayList<Reservation> defaultReservation = new ArrayList<>();
            defaultReservation.add(reservation);*//*

            while (myResultStatement.next()) {
                ArrayList<Reservation> defaultReservation = new ArrayList<>();
                //defaultReservation.add(getReservation());
                int id = myResultStatement.getInt("roomTypeId");
                h.addRoom(new Room(myResultStatement.getInt("number"), getRoomTypesById(id), getRoomReservations(myResultStatement.getInt("number"))));
                //rooms.add(new Room(myResultStatement.getInt("number"), getRoomTypesById(id), defaultReservation));

                return rooms;
            }
            //System.out.println(h.getRoom().size());

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return rooms;
    }*/

    public ArrayList<Room> roomInitiate(ArrayList<RoomType> rt) {

        ArrayList<Room> rooms = new ArrayList<>();
        try {
            Connection myConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel", "root", "password");
            Statement myStatement = myConnection.createStatement();
            ResultSet myResultStatement = myStatement.executeQuery("select * from hotel.`room`");

            while (myResultStatement.next()) {
                ArrayList<Reservation> reservations = getRoomReservations(myResultStatement.getInt("number"));
                int id = myResultStatement.getInt("roomTypeId");

                h.addRoom(new Room(myResultStatement.getInt("number"), getRoomTypesById(id), reservations));
            }
            //System.out.println(h.getRoom().size());


            // Test code
/*            String query = "select * from hotel.`room` where roomTypeId = " + 1115;
            ResultSet myResultStatement1 = myStatement.executeQuery(query);
            ArrayList<Room> roomsByType = new ArrayList<>();
            while (myResultStatement1.next()) {
                ArrayList<Reservation> reservations = getRoomReservations(myResultStatement1.getInt("number"));
                int id = myResultStatement1.getInt("roomTypeId");
                roomsByType.add(new Room(myResultStatement1.getInt("number"), getRoomTypesById(id), reservations));
            }
*/

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return rooms;
    }

    private ArrayList<Reservation> getRoomReservations(int roomNumber) {
        ArrayList<Reservation> res = new ArrayList<Reservation>();
        for (int i = 0; i < getRoomReservationsFromDB().size(); i++) {
            if (getRoomReservationsFromDB().get(i).getNumber() == roomNumber) {
                for (int j = 0; j < getReservationsFromDB().size(); j++) {
                    if (getReservationsFromDB().get(j).getId().equals(getRoomReservationsFromDB().get(i).getId())) {
                        res.add(getReservationsFromDB().get(i));
                    }
                }
            }
        }
        return res;
    }

    public ArrayList<Reservation> getReservationsFromDB() {
        ArrayList<Reservation> reservations = new ArrayList<>();
        try {

            Connection myConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel", "root", "password");
            Statement myStatement = myConnection.createStatement();
            ResultSet myResultStatement = myStatement.executeQuery("select * from hotel.`reservation`");


            while (myResultStatement.next()) {
                SimpleDateFormat sdf = new SimpleDateFormat("MM.dd.yyyy");
                Date checkIn = sdf.parse(myResultStatement.getString("checkInDate"));
                Date checkOut = sdf.parse(myResultStatement.getString("checkOutDate"));
                String name = myResultStatement.getString("name");
                String surname = myResultStatement.getString("surname");
                String guestId = myResultStatement.getString("guestId");
                reservations.add(new Reservation(myResultStatement.getString("id"), myResultStatement.getInt("numberOfGuests"), checkIn, checkOut, new Guest(guestId, name, surname)));
            }
        } catch (SQLException | ParseException e) {
            System.out.println(e.getMessage());
        }
        return reservations;
    }

    public ArrayList<RoomReservation> getRoomReservationsFromDB() {
        ArrayList<RoomReservation> roomReservations = new ArrayList<>();
        try {

            Connection myConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel", "root", "password");
            Statement myStatement = myConnection.createStatement();
            ResultSet myResultStatement = myStatement.executeQuery("select * from hotel.`roomReservation`");


            while (myResultStatement.next()) {
                roomReservations.add(new RoomReservation(myResultStatement.getString("reservationId"), myResultStatement.getInt("number")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return roomReservations;
    }


    public RoomType getRoomTypesById(int id) {
        for (RoomType roomType : roomTypes) {
            if (roomType.getId() == id) {
                return roomType;
            }
        }
        return null;
    }
}
