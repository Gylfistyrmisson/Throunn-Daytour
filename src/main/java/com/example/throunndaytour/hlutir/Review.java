package com.example.throunndaytour.hlutir;

public class Review {
    private int id;
    private String title;
    private String review;
    private Double rating;
    private int customerID;

    public Review(int id, String title, String review, Double rating, int customerID) {
        this.id = id;
        this.title = title;
        this.review = review;
        this.rating = rating;
        this.customerID = customerID;
    }
}
