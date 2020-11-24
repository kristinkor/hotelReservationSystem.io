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

        int numOfPeople;


        Hotel h = new Hotel("California", " 424 East Palm Canyon Drive, CA 92264", 5);
        System.out.println("Welcome to the hotel " + h.getName() + ". Such a lovely place!");
        System.out.println("We are located " + h.getAdress() + ". ");
        System.out.println("Current rating is " + h.getRating() + " stars!!!");


        Date checkInDate = new Date();
        getCheckInDate();
        getCheckOutDate();

        //ReservationRequest reservationRequest = new ReservationRequest(check_in, check_out, numOfPeople);

    }

    public static Date getCheckInDate() {
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

    public static void getCheckOutDate() throws Exception{
        Date check_out = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter the check-out date in format dd.MM.yyyy");
        Date date = sdf.parse(scan.nextLine());
    }
    // Instantiate a Date object
    public static boolean isValidCheckIn(Date d){
        Date todayDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        return sdf.format(d).compareTo(sdf.format(todayDate)) >= 0;
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

    public void currentRating(double r){

    }
}
