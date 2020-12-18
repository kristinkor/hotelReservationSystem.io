package com.company;


public class Guest {
    private final String id;
    private final String name;
    private final String surname;
    //private Payment payment;

    Guest(String id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        //this.payment = payment;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    @Override
    public String toString() {
        return "Guest " +
                "id " + id + '\'' +
                ", name " + name + '\'' +
                ", surname " + surname + '\'';
    }
}
