package com.company;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws Exception {
        ArrayList<RoomType> rt = new ArrayList<>();
        int numOfPeople;
        boolean cond = true;

        Hotel h = new Hotel("California", " 424 East Palm Canyon Drive, CA 92264", 5);
        System.out.println("Welcome to the hotel " + h.getName() + ". Such a lovely place!");
        System.out.println("We are located " + h.getAdress() + ". ");
        System.out.println("Current rating is " + h.getRating() + " stars!!!\n\n\n");

        ArrayList<String> f1 = new ArrayList<String>();
        //("Air conditioning", "Attached bathroom", "Flat-screen TV",  "Free WiFi", "Free toiletries", "Safe Toilet Bathtub or shower", " Towels Linens", "Socket near the bed", "Refrigerator", "Satellite channels", "Tea/Coffee maker", "Iron Interconnecting room(s) available",  "Microwave Heating", "Hairdryer", "Kitchenette", "Fan Alarm clock", "Wardrobe or closet", "Dining table", "Toilet paper", "Single-room", "AC for guest accommodation");
        f1.add("Air conditioning");
        f1.add("Attached bathroom");
        f1.add("Flat-screen TV");
        RoomType rt1 = new RoomType(1111, "Superior King Room", 197, 2, f1);
        rt.add(rt1);

        ArrayList<String> f2 = new ArrayList<String>();
        f2.add("291 square feet");
        f2.add("Attached bathroom");
        f2.add("Pool view");
        RoomType rt2 = new RoomType(1112, "Superior King Suite", 239, 2, f2);
        rt.add(rt2);

        RoomType rt3 = new RoomType(1112, "Superior King Suite", 239, 2, f2);
        rt.add(rt3);

        ArrayList<String> f3 = new ArrayList<String>();
        f3.add("Attached bathroom");
        f3.add("Pool view");
        RoomType rt4 = new RoomType(1113, "Superior Queen Room", 181, 2, f3);
        rt.add(rt4);
        // Guest g = new Guest("Kristina", "Kor", 00001);

       // Room r1 = new Room(1,rt, res);

        do{
            System.out.println("We have following types of rooms in the Hotel: ");

            for(int i = 0; i < rt.size() ; i++ ){
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
            boolean flag = true;
            do{
                int id = requestIdNumber();
                for(int i= 0; i < rt.size(); i++){

                    if(id == rt.get(i).getId()){

                        flag = false;
                    }
                }
                System.out.println("Please, enter the correct room Type Id");

            }
            while(flag);



            // show room types



            Date checkInDate = requestCheckInDate();
            Date checkOutDate = requestCheckOutDate();
            System.out.println("Enter the number of guests: ");
            int numOfGuests = getNumOfGuests();
            ReservationRequest resreq = new ReservationRequest(checkInDate, checkOutDate, numOfGuests);

           cond = false;
        }
        while(cond);

        //Reservation res = new Reservation(3, requestCheckInDate(), requestCheckOutDate(), 123, g);


        //ReservationRequest reservationRequest = new ReservationRequest(check_in, check_out, numOfPeople);

    }

    private static int requestIdNumber() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Chose the room type by entering id#: ");
        return scan.nextInt();

    }


    public static Date requestCheckInDate() {
        Date check_in = new Date();
        boolean continueInput = true;
        do {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
                Scanner scan = new Scanner(System.in);
                System.out.println("Please enter the check-in date in format dd.MM.yyyy");
                check_in = sdf.parse(scan.nextLine());

                if (isValidCheckIn(check_in)){
                    System.out.println("\nThe Check-in date is: " + check_in);
                    continueInput = false;
                }

            }
            catch(ParseException ex) {
                System.out.println("This check-in date is unavailable. Please, try again ");
            }
        }
        while(continueInput);
        return check_in;
    }

    public static Date requestCheckOutDate() throws Exception{
        Date check_out = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter the check-out date in format dd.MM.yyyy");
        Date date = sdf.parse(scan.nextLine());
        return check_out;
    }
    // Instantiate a Date object
    public static boolean isValidCheckIn(Date d){
        Date todayDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        return sdf.format(d).compareTo(sdf.format(todayDate)) >= 0;
    }

    public static int getNumOfGuests(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter the number of guests");
        return scan.nextInt();
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
