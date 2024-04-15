package com.example.throunndaytour.users;

import com.example.throunndaytour.controllers.DayTourController;
import com.example.throunndaytour.hlutir.DayTour;

public class Admin extends User {
    public Admin(int id, String name, String email, String kennitala, String password) {
        super(id, name, email, kennitala, password);
    }

    public static void deleteAll() {

    }
}
/**
    public static void createTour(DayTour dayTour) {
        DayTourController.addDayTour(dayTour);
    }
}
*/