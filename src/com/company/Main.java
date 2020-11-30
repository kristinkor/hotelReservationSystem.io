package com.company;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class Main {
    public static void main(String[] args) {
        // Creating a hotel object
        HotelInitializer init = new HotelInitializer();

        ArrayList<RoomType> roomTypes = init.roomTypeInit();
        init.roomInit(roomTypes);

        boolean cond = true;
        do {
            System.out.println(init.h.toString() + "\nWe have following types of rooms in the Hotel: ");
            System.out.println(init.h.getRoomTypes());
            //showRoomTypes(roomTypes);
            int roomTypeId = requestRoomTypeId(roomTypes);
            int numOfGuests = getNumOfGuests((getRoomTypeById(roomTypes, roomTypeId)));

            Date checkInDate = requestCheckInDate();
            Date checkOutDate = requestCheckOutDate(checkInDate);

            ReservationRequest reservationRequest = new ReservationRequest(checkInDate, checkOutDate, numOfGuests);
            boolean isPossible = init.h.isReservationPossible(getRoomTypeById(roomTypes, roomTypeId), reservationRequest);

            if (isPossible) {
                System.out.println(reservationRequest.toString());
                System.out.println("The price for your reservation request is " + init.h.calculatePrice(getRoomTypeById(roomTypes, roomTypeId), reservationRequest) +"$.");
                if (requestConfirmation()) {
                    Scanner scan = new Scanner(System.in);
                    System.out.println("Please Enter Your name ");
                    String name = scan.nextLine();
                    System.out.println("Please Enter Your surname ");
                    String surname = scan.nextLine();
                    String userId = UUID.randomUUID().toString();

                    //reservations.add(new Reservation(reservations.size(), numOfGuests, checkInDate, checkOutDate, new Guest(name, surname, userId)));
                }
            }
            else {
                System.out.println("this room type is not available ");
                cond = false;
            }

        }
        while (cond);
    }

    /** The method requestRoomTypeId asks user to enter room type id and validates the input
     * @param rt is a list with room types
     * */
    public static int requestRoomTypeId(ArrayList<RoomType> rt) {
        Scanner scan = new Scanner(System.in);
        boolean flag = true;
        int roomTypeId = 0;

        do {
            try {
                System.out.println("Chose the room type by entering id#: ");
                roomTypeId = scan.nextInt();
                for (RoomType roomType : rt) {
                    if (roomTypeId == roomType.getId()) {
                        flag = false;
                        break;
                    }
                }
            } catch (InputMismatchException e) {
                System.out.println("Please, enter the correct room Type Id");
                scan.next();
            }
        }
        while (flag);
        return roomTypeId;
    }

    /** The method requestCheckInDate asks user to enter check-in date and validates the input
     * @return valid check-in date
     * */
    public static Date requestCheckInDate() {
        Date checkInDate = new Date();

        boolean flag = true;
        do {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("MM.dd.yyyy");
                Scanner scan = new Scanner(System.in);
                System.out.println("Please enter the check-in date in format MM.dd.yyyy");
                checkInDate = sdf.parse(scan.nextLine());

                if (isValidCheckIn(checkInDate)) {
                    System.out.println("\nThe Check-in date is: " + checkInDate);
                    flag = false;
                }

            } catch (ParseException ex) {
                System.out.println("This check-in date is unavailable. Please, try again ");
            }
        }
        while (flag);

        return checkInDate;
    }

    public static boolean isValidCheckIn(Date start) {
        Date todayDate = new Date();
        Date endOfBookingPeriodDate = addDays(todayDate, 30);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        return sdf.format(start).compareTo(sdf.format(todayDate)) >= 0 && sdf.format(start).compareTo(sdf.format(endOfBookingPeriodDate)) <= 0;
    }

    /** The method requestCheckOutDate asks user to enter check-in date and validates the input
     * @return valid check-out date
     * */
    public static Date requestCheckOutDate(Date checkInDate) {
        Date checkOutDate = new Date();

        boolean continueInput = true;
        do {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("MM.dd.yyyy");
                Scanner scan = new Scanner(System.in);
                System.out.println("Please enter the check-out date in format MM.dd.yyyy");
                checkOutDate = sdf.parse(scan.nextLine());

                if (isValidCheckOut(checkInDate, checkOutDate)) {
                    System.out.println("\nThe Check-out date is: " + checkOutDate);
                    continueInput = false;
                }

            } catch (ParseException ex) {
                System.out.println("This check-in date is unavailable. Please, try again ");
            }
        }
        while (continueInput);
        return checkOutDate;
    }

    public static boolean isValidCheckOut(Date start, Date end) {
        Date todayDate = new Date();
        Date endOfBookingPeriodDate = addDays(todayDate, 30);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        return sdf.format(end).compareTo(sdf.format(start)) >= 0 && sdf.format(end).compareTo(sdf.format(endOfBookingPeriodDate)) <= 0;
    }

    /** The method requestCheckOutDate asks user to enter check-in date and validates the input
     * @return valid check-out date
     * */
    public static int getNumOfGuests(RoomType rt) {
        do {
            try {
                Scanner scan = new Scanner(System.in);
                System.out.println("Please enter the number of guests");
                int numOfGuests = scan.nextInt();
                if(numOfGuests <= rt.getCapacity()){
                    return numOfGuests;
                }
                else {
                    System.out.println("The number of people you entered is incorrect. ");
                }
            } catch (InputMismatchException e) {
                System.out.println("Wrong input! ");
            }
        }
        while (true);
    }

    public static Date addDays(Date d, int days) {
        Calendar calendar = new GregorianCalendar(/* remember about timezone! */);
        calendar.setTime(d);
        calendar.add(Calendar.DATE, 30);
        d = calendar.getTime();
        return d;
    }

    public static RoomType getRoomTypeById(ArrayList<RoomType> rt, int id) {
        int i;
        try {
            for (i = 0; i < rt.size(); i++) {
                if (rt.get(i).getId() == id) {
                    break;
                }

            }
            return rt.get(i);
        } catch (Exception e) {
            System.out.println("Wrong input");
        }
        return null;
    }

    private static boolean requestConfirmation() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Would you like to make a reservation? Please press Y or N ");
        String userInput = scan.nextLine();
        return userInput.compareToIgnoreCase("Y") == 0;
    }
}
