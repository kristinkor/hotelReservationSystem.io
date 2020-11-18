package com.company;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Search {
    Date check_in = new Date();
    Date check_out = new Date();
    int numOfPeople;
    Date todayDate = new Date();





    public void getCheckInDate() {
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
    }

    public void getCheckOutDate() throws Exception{
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter the check-out date in format dd.MM.yyyy");
        Date date = sdf.parse(scan.nextLine());
    }

    public void setCheckInDate(Date date){
        this.check_in = date;
    }

    // Instantiate a Date object
    public boolean isValidCheckIn(Date d){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        return sdf.format(d).compareTo(sdf.format(todayDate)) >= 0;
    }

    public boolean isValidRange(Date d1, Date d2){
        return check_in.before(check_out);
    }

    public void calculateRange(Date date1, Date date2){
        long diff = date2.getTime() - date1.getTime();
        System.out.println ("Days: " + TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS));
    }


    public void showDate(){

        // display time and date using toString()
        System.out.println("Check-in: " + check_in.toString() + " Check-out: " + check_out.toString());
    }
}
