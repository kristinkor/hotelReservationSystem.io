package com.company;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws Exception {

        // Creating a hotel object
        Hotel h = new Hotel("California", " 424 East Palm Canyon Drive, CA 92264", 5);
        System.out.println(h.toString());

        //Creating RoomType object with facilities
        ArrayList<String> facilities = new ArrayList<>();
        facilities.add("Air conditioning");
        ArrayList<RoomType> roomTypes = new ArrayList<>();
        RoomType roomType = new RoomType(1111, "Superior King Room", 197, 2, facilities);
        roomTypes.add(roomType);

        // Creating reservation & Guest
        Date checkIn = new Date(2020, Calendar.DECEMBER, 1);
        Date checkOut = new Date(2020, Calendar.DECEMBER, 5);

        // Creating a Guest object
        Guest guest = new Guest("Kristina", "Kor", 1);
        ArrayList <Guest> guests = new ArrayList<>();
        guests.add(guest);

        Reservation reservation = new Reservation(1, 2, checkIn, checkOut, guest);
        ArrayList<Reservation> reservations= new ArrayList<Reservation>();
        reservations.add(reservation);

        Room room1 = new Room(1,roomType, reservations);
        h.addRoom(room1);

        System.out.println(h.toString());


        int numOfPeople;
        boolean cond = true;


        ArrayList<String> f1 = new ArrayList<>();
        //("Air conditioning", "Attached bathroom", "Flat-screen TV",  "Free WiFi", "Free toiletries", "Safe Toilet Bathtub or shower", " Towels Linens", "Socket near the bed", "Refrigerator", "Satellite channels", "Tea/Coffee maker", "Iron Interconnecting room(s) available",  "Microwave Heating", "Hairdryer", "Kitchenette", "Fan Alarm clock", "Wardrobe or closet", "Dining table", "Toilet paper", "Single-room", "AC for guest accommodation");
        f1.add("Air conditioning");
        f1.add("Attached bathroom");
        f1.add("Flat-screen TV");
        RoomType rt1 = new RoomType(1111, "Superior King Room", 197, 2, f1);
        roomTypes.add(rt1);



       // Room r1 = new Room(1,rt, res);

        do{
            System.out.println("We have following types of rooms in the Hotel: ");

            // show room types
            showRoomTypes(roomTypes);

            // ask user to enter the id of the room he wants to book
            int roomId = requestIdNumber(roomTypes);
            RoomType roomTypeRequest = getRoomTypeById(roomTypes, roomId);
            boolean flag = true;
            do {
                // ask user for a date of check-in
                Date checkInDate = requestCheckInDate();
                Date checkOutDate = requestCheckOutDate(checkInDate);
                System.out.println("Enter the number of guests: ");
                int numOfGuests = getNumOfGuests();
                ReservationRequest resRequest = new ReservationRequest(checkInDate, checkOutDate, numOfGuests);

                if(h.isReservationPossible(roomTypeRequest,resRequest)){
                    System.out.println(resRequest.toString());
                    System.out.println("The price for " + resRequest.toString() + " is" + h.calculatePrice(roomTypeRequest, resRequest));

                    flag = false;
                }
                if(requestConfirmation()){

                    Scanner scan = new Scanner(System.in);
                    System.out.println("Please Enter Your name ");
                    String name = scan.nextLine();
                    System.out.println("Please Enter Your surname ");
                    String surname = scan.nextLine();

                    Guest guest1 = new Guest(name, surname, guests.size() + 1);
                    Reservation reservation1 = new Reservation(reservations.size(), numOfGuests, checkInDate, checkOutDate, guest1);
                }
            }
            while(true);
            //int id =1;




        }
        while(cond);
    }


    private static boolean requestConfirmation() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Would you like to make a reservation? Please press Y or N ");
        String userInput = scan.nextLine();
        if(userInput.compareToIgnoreCase("Y") == 0){
            return true;
        }
        return false;
    }

    private static RoomType getRoomTypeById(ArrayList<RoomType> rt, int id) {
        int i;
        for(i = 0; i < rt.size(); i++ ){
            if(rt.get(i).getId() == rt.get(id).getId()){
                break;
            }

        }
        return rt.get(i);
    }

    private static void showRoomTypes(ArrayList<RoomType> rt) {
        for(int i = 0; i < rt.size(); i++ ){
            int j;
            for(j = 0; j < rt.size(); j++){
                if(rt.get(i).getId() == rt.get(j).getId()){
                    break;
                }
            }
            if (i == j){
                System.out.print(rt.get(i));
            }
        }
    }

    private static int requestIdNumber(ArrayList<RoomType> rt) {
        Scanner scan = new Scanner(System.in);
        boolean flag = true;
        int roomId = 0;

        do{
            try {
                System.out.println("Chose the room type by entering id#: ");
                roomId = scan.nextInt();
                for(int i= 0; i < rt.size(); i++){
                    if (roomId == rt.get(i).getId()) {
                        flag = false;
                        break;
                    }
                }
            } catch (InputMismatchException e){
                System.out.println("Please, enter the correct room Type Id");
                scan.next();
            }
        }
        while(flag);
        return roomId;
    }


    public static Date requestCheckInDate() {
        Date checkInDate = new Date();

        boolean continueInput = true;
        do {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("MM.dd.yyyy");
                Scanner scan = new Scanner(System.in);
                System.out.println("Please enter the check-in date in format MM.dd.yyyy");
                checkInDate = sdf.parse(scan.nextLine());

                if (isValidCheckIn(checkInDate)){
                    System.out.println("\nThe Check-in date is: " + checkInDate);
                    continueInput = false;
                }

            }
            catch(ParseException ex) {
                System.out.println("This check-in date is unavailable. Please, try again ");
            }
        }
        while(continueInput);
        return checkInDate;
    }

    public static Date addDays(Date d, int days)
    {
        Calendar calendar = new GregorianCalendar(/* remember about timezone! */);
        calendar.setTime(d);
        calendar.add(Calendar.DATE, 30);
        d = calendar.getTime();
        return d;
    }

    public static Date requestCheckOutDate(Date checkInDate) throws Exception{
        Date checkOutDate = new Date();

        boolean continueInput = true;
        do {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("MM.dd.yyyy");
                Scanner scan = new Scanner(System.in);
                System.out.println("Please enter the check-out date in format MM.dd.yyyy");
                checkOutDate = sdf.parse(scan.nextLine());

                if (isValidCheckOut(checkInDate, checkOutDate)){
                    System.out.println("\nThe Check-out date is: " + checkOutDate);
                    continueInput = false;
                }

            }
            catch(ParseException ex) {
                System.out.println("This check-in date is unavailable. Please, try again ");
            }
        }
        while(continueInput);
        return checkOutDate;
    }

    // Instantiate a Date object
    public static boolean isValidCheckIn(Date start){
        Date todayDate = new Date();
        Date endOfBookingPeriodDate = addDays(todayDate, 30);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        return sdf.format(start).compareTo(sdf.format(todayDate)) >= 0 && sdf.format(start).compareTo(sdf.format(endOfBookingPeriodDate)) <= 0;
    }

    public static boolean isValidCheckOut(Date start, Date end){
        Date todayDate = new Date();
        Date endOfBookingPeriodDate = addDays(todayDate, 30);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        return sdf.format(end).compareTo(sdf.format(start)) >= 0 && sdf.format(end).compareTo(sdf.format(endOfBookingPeriodDate)) <= 0;
    }

    public static int getNumOfGuests(){
        boolean flag = true;
        do {
            try {
                Scanner scan = new Scanner(System.in);
                System.out.println("Please enter the number of guests");
                return scan.nextInt();

            }
            catch (Exception e) {
                 System.out.println("Wrong input! ");

            }
        }
        while (flag);
        return 0;
    }

/*    public boolean isValidRange(Date d1, Date d2){
        return check_in.before(check_out);
    }*/

    public void calculateRange(Date date1, Date date2){
        long diff = date2.getTime() - date1.getTime();
        System.out.println ("Days: " + TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS));
    }


 /*   public void showDate(){

        // display time and date using toString()
        System.out.println("Check-in: " + check_in.toString() + " Check-out: " + check_out.toString());
    }*/

}
