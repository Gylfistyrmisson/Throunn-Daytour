package com.example.throunndaytour.users;

import com.example.throunndaytour.hlutir.Booking;
import com.example.throunndaytour.hlutir.DayTour;

public class User {
    private int id;
    private String name = null;
    private String email;
    private String kennitala;
    private String password;
    private int daytourCNT;
    private int[] dayTours;

    public User(int id, String name, String email, String kennitala, String password,int daytourCNT,int[] dayTours) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.kennitala = kennitala;
        this.password = password;
        this.daytourCNT = daytourCNT;
        this.dayTours = dayTours;
    }
    public static String getEmail(User user) {
        return user.email;
    }

    public void addBooking(int dayTour) {
        int[] nyttFylki = new int[daytourCNT+1];
        for (int i = 0; i < daytourCNT+1;i++) {
            nyttFylki[i] = dayTours[i];
        }
        nyttFylki[daytourCNT] = dayTour;
        dayTours = nyttFylki;
        daytourCNT++;
    }

    public String getName () {
        return name;
    }

    public int getId () {
        return id;
    }
}
