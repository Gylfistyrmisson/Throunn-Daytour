package com.example.throunndaytour.hlutir;

public class Booking {
    private int bookingID;
    private int userID;
    private int daytourID;

    public Booking (int bookingID,int userID, int daytourID) {
        this.bookingID = bookingID;
        this.userID = userID;
        this.daytourID = daytourID;
    }
}
