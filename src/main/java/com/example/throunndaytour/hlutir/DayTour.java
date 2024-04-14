package com.example.throunndaytour.hlutir;
import java.util.Date;

public class DayTour {
    private int id;
    private String name;
    private int price;
    private int duration;
    private int[] date;
    private String location;
    private int customerCNT;
    private int[] customers = {};
    private int reviewCNT;
    private int[] reviews = {};

    public DayTour(int id, String name, int price, int duration, int[] date, String location,int customerCNT,int[] customers,int reviewCNT,int[] reviews) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.duration = duration;
        this.date = date;
        this.location = location;
        this.customers = customers;
        this.reviews = reviews;
        this.customerCNT = customerCNT;
        this.reviewCNT = reviewCNT;

    }

    public void addCustomer(int customerID) {
        int fylkilengd = customers.length;
        int[] fylkinytt = new int[fylkilengd+1];
        System.arraycopy(customers, 0, fylkinytt, 0, fylkilengd);
        fylkinytt[fylkilengd] = customerID;
        customers = fylkinytt;
        customerCNT++;
    }

    public void addReview(int reviewID) {
        int fylkilengd = reviews.length;
        int[] fylkinytt = new int[fylkilengd+1];
        System.arraycopy(reviews, 0, fylkinytt, 0, fylkilengd);
        fylkinytt[fylkilengd] = reviewID;
        reviews = fylkinytt;
        reviewCNT++;
    }
}
