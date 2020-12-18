package com.company;

import java.util.ArrayList;


public class RoomType {
    private int id;
    private String name;
    private double dailyRate;
    private int capacity;
    private ArrayList<String> facilities;

    public RoomType(int id, String name, double dailyRate, int capacity, ArrayList<String> facilities) {
        this.id = id;
        this.name = name;
        this.dailyRate = dailyRate;
        this.capacity = capacity;
        this.facilities = facilities;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getDailyRate() {
        return dailyRate;
    }

    public void setDailyRate(double dailyRate) {
        this.dailyRate = dailyRate;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public ArrayList<String> getFacilities() {
        return facilities;
    }

    @Override
    public String toString() {
        return "RoomType id#" + id + " is a " + name +
                " for only " + dailyRate +
                "$ per day. The capacity is " + capacity +
                " people. The room has " + facilities + "\n";
    }
}
