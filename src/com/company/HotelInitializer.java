package com.company;

import java.sql.*;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


import java.sql.Connection;


public class HotelInitializer {
    // Initialize hotel
    Hotel h = new Hotel("California", " 424 East Palm Canyon Drive, CA 92264", 5);

    public static void main(String[] args) throws SQLException {
    }

    ArrayList<RoomType> roomTypes = new ArrayList<>();


    public ArrayList<RoomType> roomTypeInit() {
        try {

            Connection myConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel", "root", "password");
            Statement myStatement = myConnection.createStatement();
            ResultSet myResultStatement = myStatement.executeQuery("select * from hotel.`roomType`");


            while (myResultStatement.next()) {
                ArrayList<String> facilitiesKingRoom = new ArrayList<>();
                facilitiesKingRoom.add("hop");
                roomTypes.add(new RoomType(myResultStatement.getInt("id"), myResultStatement.getString("name"),
                        myResultStatement.getDouble("dailyRate"), myResultStatement.getInt("capacity"), facilitiesKingRoom));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        //System.out.println(roomTypes.size());
        return roomTypes;
    }

    public int saveReservations(Reservation reservation, Guest guest) throws SQLException {
        Connection myConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel", "root", "password");

        try {

            PreparedStatement pr = myConnection.prepareStatement("insert into "
                    + "reservation(id, numberOfGuests, checkInDate, checkOutDate, name, surname) values(?,?,?,?,?,?)");
            pr.setString(1, reservation.getId());

            pr.setInt(2, reservation.getNumberOfGuests());
            Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String checkIn = formatter.format(reservation.getCheckInDate());
            System.out.println(checkIn);
            String checkOut = formatter.format(reservation.getCheckOutDate());
            pr.setString(3, checkIn);
            pr.setString(4, checkOut);
            pr.setString(5, guest.getName());
            pr.setString(6, guest.getSurname());
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
        //чтобы контролировать выполнение запроса можно выводить переменную в зависимости от успешность или неудачи выполнения запроса.
        //можно также выводить айди объекта, который был добавлен в базу.
    }

    public void roomInit(ArrayList<RoomType> rt) {
        ArrayList<Room> rooms = new ArrayList<>();
        try {

            Connection myConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel", "root", "password");
            Statement myStatement = myConnection.createStatement();
            ResultSet myResultStatement = myStatement.executeQuery("select * from hotel.`room`");

/*            Date checkIn = new java.util.Date("Wed Dec 20 00:00:00 EST 2020");
            Date checkOut = new java.util.Date("Wed Dec 25 00:00:00 EST 2020");
            Guest g = new Guest("5", "a", "b");

            Reservation reservation = new Reservation("1", 2, checkIn, checkOut, g);
            ArrayList<Reservation> defaultReservation = new ArrayList<>();
            defaultReservation.add(reservation);*/

            while (myResultStatement.next()) {
                ArrayList<Reservation> defaultReservation = new ArrayList<>();
                //defaultReservation.add(getReservation());
                int id = myResultStatement.getInt("roomTypeId");
                h.addRoom(new Room(myResultStatement.getInt("number"), getRoomTypesById(id), defaultReservation));
            }
            //System.out.println(h.getRoom().size());

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /*private void getReservation() throws SQLException {
        try {
            Connection myConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel", "root", "password");
            Statement myStatement = myConnection.createStatement();
            ResultSet myResultStatement = myStatement.executeQuery("select * from hotel.`roomReservation`");
        }
        catch (SQLException e) {
        System.out.println(e.getMessage());
        }
    }*/

    public ArrayList<Reservation> getReservationsFromDB() {
        ArrayList<Reservation> reservations = new ArrayList<>();
        try {

            Connection myConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel", "root", "password");
            Statement myStatement = myConnection.createStatement();
            ResultSet myResultStatement = myStatement.executeQuery("select * from hotel.`reservation`");


            while (myResultStatement.next()) {
                SimpleDateFormat sdf = new SimpleDateFormat("MM.dd.yyyy");
                Date checkIn = (Date) sdf.parse(myResultStatement.getString("checkInDate"));
                Date checkOut = (Date) sdf.parse(myResultStatement.getString("checkOutDate"));
                String name = myResultStatement.getString("name");
                String surname = myResultStatement.getString("surname");
                String guestId = myResultStatement.getString("guestId");
                reservations.add(new Reservation(myResultStatement.getString("id"), myResultStatement.getInt("numberOfGuests"),checkIn,checkOut,new Guest(guestId, name, surname)));
            }
        } catch (SQLException | ParseException e) {
            System.out.println(e.getMessage());
        }
        //System.out.println(roomTypes.size());
        return reservations;
    }

    public ArrayList<Reservation> getRoomReservationsFromDB() {
        ArrayList<Reservation> reservations = new ArrayList<>();
        try {

            Connection myConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel", "root", "password");
            Statement myStatement = myConnection.createStatement();
            ResultSet myResultStatement = myStatement.executeQuery("select * from hotel.`reservation`");


            while (myResultStatement.next()) {
                SimpleDateFormat sdf = new SimpleDateFormat("MM.dd.yyyy");
                Date checkIn = (Date) sdf.parse(myResultStatement.getString("checkInDate"));
                Date checkOut = (Date) sdf.parse(myResultStatement.getString("checkOutDate"));
                String name = myResultStatement.getString("name");
                String surname = myResultStatement.getString("surname");
                String guestId = myResultStatement.getString("guestId");
                reservations.add(new Reservation(myResultStatement.getString("id"), myResultStatement.getInt("numberOfGuests"),checkIn,checkOut,new Guest(guestId, name, surname)));
            }
        } catch (SQLException | ParseException e) {
            System.out.println(e.getMessage());
        }
        //System.out.println(roomTypes.size());
        return reservations;
    }



    public RoomType getRoomTypesById(int id) {
        System.out.println(id);
        //System.out.println(id);
        for (RoomType roomType : roomTypes) {
            if (roomType.getId() == id) {
                return roomType;
            }
        }
        return null;
    }
}
