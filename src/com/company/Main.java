package com.company;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

//import java.sql.*;

public class Main{
    public static void main(String[] args) throws SQLException {
        // Creating a hotel object
        HotelInitializer init = new HotelInitializer();
        //jdbc.Driver driver = new Driver();

        ArrayList<RoomType> roomTypes = init.roomTypeInit();
        init.roomInit(roomTypes);

        boolean condition = true;
        do {
            System.out.println(init.h.toString() + "\nWe have following types of rooms in the Hotel: \n" + init.h.getRoomTypes());

            int roomTypeId = requestRoomTypeId(roomTypes);
            RoomType roomTypeRequest = (getRoomTypeById(roomTypes, roomTypeId));
            int numOfGuests = getNumOfGuests(roomTypeRequest);

            Date checkInDate = requestCheckInDate();
            Date checkOutDate = requestCheckOutDate(checkInDate);

            ReservationRequest reservationRequest = new ReservationRequest(checkInDate, checkOutDate, numOfGuests);
            boolean isPossible = init.h.isReservationPossible(getRoomTypeById(roomTypes, roomTypeId), reservationRequest);

            if (isPossible) {
                System.out.println(reservationRequest.toString());
                double totalCost = init.h.calculatePrice(roomTypeRequest, reservationRequest);
                System.out.println("You can book this room! The price for your reservation request is " + totalCost + "$.");
                System.out.println("Would you like to make a reservation? Please press Y or N ");
                if (requestConfirmation()) {
                    Guest guest = requestGuestInformation();
                    //requestPayment(totalCost);
                    String id = generateId();
                    Reservation reservation = new Reservation(id, numOfGuests, checkInDate, checkOutDate, guest);
                    if(init.saveReservations(reservation, guest)==1) {
                        System.out.println("Success");
                    }else  {
                        System.out.println("Shit happens");
                    }

                    init.h.addReservation(roomTypeRequest, reservation);

                    System.out.println("Congratulations! Your booking is made. ");
                    System.out.println("Your reservation: \n" + reservation.toString());
                }
            } else {
                System.out.println("this room type is not available. ");
            }
            System.out.println("Would you like to make another reservation? Please press Y or N ");
            if (!requestConfirmation()) {
                condition = false;
            }

        }
        while (condition);
    }


    private static Guest requestGuestInformation() {
        do {
            try {
                Scanner scan = new Scanner(System.in);
                System.out.println("Please, Enter the name of the Guest who is making reservation ");
                String name = scan.nextLine();
                System.out.println("Please Enter the surname ");
                String surname = scan.nextLine();
                String userId = generateId();
                return new Guest(userId, name, surname);
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please try again.");
            }

        }
        while (true);
    }

    //cryptographically strong pseudo random number generator
    private static String generateId() {
        return UUID.randomUUID().toString();
    }

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
                    System.out.println("The Check-in date is: " + checkInDate);
                    flag = false;
                }

            } catch (Exception ex) {
                System.out.println("This check-in date is unavailable. Please, try again ");
            }
        }
        while (flag);

        return checkInDate;
    }

    public static boolean isValidCheckIn(Date startDate) {
        Date todayDate = new Date();
        Date endOfBookingPeriodDate = addDays(todayDate, 30);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        return sdf.format(startDate).compareTo(sdf.format(todayDate)) >= 0 && sdf.format(startDate).compareTo(sdf.format(endOfBookingPeriodDate)) <= 0;
    }

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
                    System.out.println("The Check-out date is: " + checkOutDate);
                    continueInput = false;
                }

            } catch (Exception ex) {
                System.out.println("This check-in date is unavailable. Please, try again ");
            }
        }
        while (continueInput);
        return checkOutDate;
    }

    public static boolean isValidCheckOut(Date startDate, Date endDate) {
        Date endOfBookingPeriodDate = addDays(startDate, 30);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        return sdf.format(endDate).compareTo(sdf.format(startDate)) > 0 && sdf.format(endDate).compareTo(sdf.format(endOfBookingPeriodDate)) < 0;
    }

    public static int getNumOfGuests(RoomType roomType) {
        do {
            try {
                Scanner scan = new Scanner(System.in);
                System.out.println("Please enter the number of guests");
                int numOfGuests = scan.nextInt();
                if (numOfGuests <= roomType.getCapacity()) {
                    return numOfGuests;
                } else {
                    System.out.println("The number of people you entered is incorrect. ");
                }
            } catch (InputMismatchException e) {
                System.out.println("Wrong input! ");
            }
        }
        while (true);
    }

    public static Date addDays(Date date, int days) {
        Calendar calendar = new GregorianCalendar(/* remember about timezone! */);
        calendar.setTime(date);
        calendar.add(Calendar.DATE, 30);
        date = calendar.getTime();
        return date;
    }

    public static RoomType getRoomTypeById(ArrayList<RoomType> roomTypes, int id) {
        int i;
        try {
            for (i = 0; i < roomTypes.size(); i++) {
                if (roomTypes.get(i).getId() == id) {
                    break;
                }

            }
            return roomTypes.get(i);
        } catch (Exception e) {
            System.out.println("Wrong input");
        }
        return null;
    }

    private static boolean requestConfirmation() {
        Scanner scan = new Scanner(System.in);
        String userInput = scan.nextLine();
        return userInput.compareToIgnoreCase("Y") == 0;
    }

    private static void requestPayment(double totalCost) {
        boolean flag = true;
        do {
            try {
                switch (requestPaymentMethod()) {
                    case 1:
                        requestCardPayment(totalCost);
                        flag = false;
                        break;
                    case 2:
                        requestPayPalPayment(totalCost);
                        flag = false;
                        break;
                    case 3:
                        requestCheckPayment(totalCost);
                        flag = false;
                        break;
                }
            } catch (Exception e) {
                System.out.println("Huston. We have a problem ");
            }

        } while (flag);

    }

    private static int requestPaymentMethod() {
        Scanner scan = new Scanner(System.in);
        int paymentMethod;
        do {
            try {
                System.out.println("Please type one of the following payment methods: 1 for Card, 2 for PayPal, or 3 for Check ");
                paymentMethod = scan.nextInt();
                if (paymentMethod > 0 && paymentMethod < 4) {
                    return paymentMethod;
                }

            } catch (InputMismatchException e) {
                System.out.println("Invalid input ");
                scan.nextInt();
            }

        } while (true);
    }

    private static void requestCheckPayment(double totalCost) {
        Scanner scan = new Scanner(System.in);
        boolean flag = true;
        do {
            try {
                System.out.println("Please enter the first name that appears on the check: ");
                String userBillingNameFirst = scan.next();
                System.out.println("Please enter the last name that appears on the check: ");
                String userBillingNameLast = scan.next();
                System.out.println("Please enter the address of the check holder: ");
                String userBillingAddress = scan.next();
                userBillingAddress += scan.nextLine();
                System.out.println("Please enter the check routing number: ");
                String userRoutingNumber = scan.next();
                System.out.println("Please enter the account number: ");
                String userAccountNumber = scan.next();
                System.out.println("Please enter the check number: ");
                String userCheckNumber = scan.next();

                Payment payment = new CashPayment(userRoutingNumber, userAccountNumber, userCheckNumber, userBillingNameFirst, userBillingNameLast, userBillingAddress, totalCost);
                System.out.println("Please review the following information you have entered.");
                ((CashPayment) payment).displayInfo(); // casting to access displayInfo method
                System.out.println("Is the information correct? Type Y for yes, N for no.");
                String ans = scan.nextLine();
                if (ans.equalsIgnoreCase("Y")) {
                    flag = false;
                } else {
                    System.out.println("Please enter the information again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input, try again");
            }
        }
        while (flag);
    }

    private static void requestPayPalPayment(double totalCost) {
        Scanner scan = new Scanner(System.in);
        boolean flag = true;
        do {
            try {
                System.out.println("Please enter the email address associated with the PayPal account: ");
                String userEmail = scan.next();
                System.out.println("Please enter the first name of the PayPal account holder: ");
                String userBillingNameFirst = scan.next();
                System.out.println("Please enter the last name of the PayPal account holder: ");
                String userBillingNameLast = scan.next();
                System.out.println("Please enter the billing address of the PayPal account holder: ");
                String userBillingAddress = scan.next();
                userBillingAddress += scan.nextLine();

                Payment payment = new PayPalPayment(userEmail, userBillingNameFirst, userBillingNameLast, userBillingAddress, totalCost);
                System.out.println("Please review the following information you have entered.");
                ((PayPalPayment) payment).displayInfo(); //casting to access displayInfo method
                System.out.println("Is the information correct? Type Y for yes, N for no.");
                String ans = scan.nextLine();
                if (ans.equalsIgnoreCase("Y")) {
                    flag = false;
                } else {
                    System.out.println("Please enter the information again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input, try again");
            }
        }
        while (flag);
    }

    private static void requestCardPayment(double totalCost) {
        Scanner scan = new Scanner(System.in);
        boolean flag = true;
        do {
            try {
                System.out.print("Please enter the card type: ");
                String userCardType = scan.next();
                System.out.print("Please enter the first name on the card: ");
                String userBillingNameFirst = scan.next();
                System.out.print("Please enter the last name on the card: ");
                String userBillingNameLast = scan.next();
                System.out.print("Please enter the card number with no spaces: ");
                String userCardNumber = scan.next();
                System.out.print("Please enter the cvv2 security code of the card: ");
                int userCvv2 = scan.nextInt();
                while (userCvv2 > 9999) {
                    System.out.print("Please enter a valid cvv2 security code (4 digit limit): ");
                    userCvv2 = scan.nextInt();
                }
                System.out.println("Please enter the month the card expires: ");
                int userCardExpMonth = scan.nextInt();
                while (userCardExpMonth > 12) {
                    System.out.println("Please enter a valid month (1-12): "); //month exp can only be from 1-12 on real cards
                    userCardExpMonth = scan.nextInt();
                }
                System.out.println("Please enter the year the card expires: "); //on cards, exp year is only last two digits. there, for the century of 2000, 2099 is last valid year. 99 is highest possible value
                int userCardExpYear = scan.nextInt();
                while (userCardExpYear > 99) {
                    System.out.println("Please enter a valid year the card expires (20-99): ");
                    userCardExpYear = scan.nextInt();
                }
                System.out.println("Please enter your billing address: ");
                String userBillingAddress = scan.next();
                userBillingAddress += scan.nextLine();
                Payment payment = new CardPayment(userCardType, userCardNumber, userCvv2, userCardExpMonth, userCardExpYear, userBillingNameFirst, userBillingNameLast, userBillingAddress, totalCost);
                System.out.println("Please review the following information you have entered.");
                ((CardPayment) payment).displayInfo(); //casting to access displayInfo method
                System.out.println("Is the information correct? Type Y for yes, N for no.");
                String ans = scan.nextLine();
                if (ans.equalsIgnoreCase("Y")) {
                    flag = false;
                } else {
                    System.out.println("Please enter the information again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input, try again");
            }
        }
        while (flag);
    }
}
