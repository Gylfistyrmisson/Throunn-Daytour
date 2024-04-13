package com.example.throunndaytour.hlutir;

public class Booking {
    private int kennitala;
    private int daytourID;
    private boolean hotelPickup;

    public Booking (int kennitala, int daytourID, boolean hotelPickup) {
        this.kennitala = kennitala;
        this.daytourID = daytourID;
        this.hotelPickup = hotelPickup;
    }
}
