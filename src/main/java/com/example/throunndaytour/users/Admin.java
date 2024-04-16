package com.example.throunndaytour.users;

import com.example.throunndaytour.controllers.DayTourController;
import com.example.throunndaytour.database.DatabaseDaytour;
import com.example.throunndaytour.hlutir.DayTour;

import java.util.Date;

public class Admin extends User {
    public Admin(int id, String name, String email, String kennitala, String password) {
        super(id, name, email, kennitala, password);
    }

    public static void deleteAll() {

    }

    public static void editAll() {

    }

    public static void createTour(DayTour dayTour) {
        DatabaseDaytour.createDayTour(dayTour.getName(), dayTour.getPrice(), dayTour.getDuration(), dayTour.getDate(),
                dayTour.getLocation());
    }

    public static void deleteTour(DayTour dayTour) {
        //DayTourController.removeDayTour(dayTour.getId());
    }

    public static void editTourID(DayTour dayTour, int id) {
        dayTour.setId(id);
    }

    public static void editTourName(DayTour dayTour, String name) {
        dayTour.setName(name);
    }

    public static void editTourPrice(DayTour dayTour, int price) {
        dayTour.setPrice(price);
    }

    public static void editTourDuration(DayTour dayTour, int duration) {
        dayTour.setDuration(duration);
    }

    public static void editTourDate(DayTour dayTour, int[] date) {
        dayTour.setDate(date);
    }

    public static void editTourLocation(DayTour dayTour, String location) {
        dayTour.setLocation(location);
    }

}
