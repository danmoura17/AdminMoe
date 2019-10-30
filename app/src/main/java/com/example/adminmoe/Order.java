package com.example.adminmoe;

public class Order {
    private String Attendant;
    private String Date;

    public Order() {
    }

    public Order(String attendant, String date) {
        Attendant = attendant;
        Date = date;
    }

    public String getAttendant() {
        return Attendant;
    }

    public void setAttendant(String attendant) {
        Attendant = attendant;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }
}
