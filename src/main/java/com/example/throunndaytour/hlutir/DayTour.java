package com.example.throunndaytour.hlutir;
import java.util.Date;

public class DayTour {
    private int id;
    private String name;
    private int price;
    private int duration;
    private Date date;
    private String location;
    private boolean hotelPickup;
    private int[] customers = {};
    private Review[] reviews = {};

    public DayTour(int id, String name, int price, int duration, Date date, String location, boolean hotelPickup) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.duration = duration;
        this.date = date;
        this.location = location;
        this.hotelPickup = hotelPickup;
    }

    public void addCustomer(int customerID) {
        int fylkilengd = customers.length;
        int[] fylkinytt = new int[fylkilengd+1];
        for (int i = 0; i < fylkilengd; i++) {
            fylkinytt[i] = customers[i];
        }
        fylkinytt[fylkilengd] = customerID;
        customers = fylkinytt;
    }

    public void addReview(Review review) {
        int fylkilengd = reviews.length;
        Review[] fylkinytt = new Review[fylkilengd+1];
        for (int i = 0; i < fylkilengd; i++) {
            fylkinytt[i] = reviews[i];
        }
        fylkinytt[fylkilengd] = review;
        reviews = fylkinytt;
    }
}
