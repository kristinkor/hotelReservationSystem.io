package com.company;

import java.util.ArrayList;

public class RoomType {
    private String name;
    private double dailyRate;
    private int capacity;
    private ArrayList <String> facilities;

    RoomType(String name, double dailyRate , int capacity, ArrayList<String> facilities){
        this.name = name;
        this.dailyRate = dailyRate;
        this.capacity = capacity;
        this.facilities = facilities;
    }



    public String getName(){
        return name;
    }

    public double getDailyRate() {
        return dailyRate;
    }

    public int getCapacity() {
        return capacity;
    }

    public ArrayList <String> getFacilities(){
        return facilities;
    }
}
