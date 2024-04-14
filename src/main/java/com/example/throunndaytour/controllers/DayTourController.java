package com.example.throunndaytour.controllers;

import com.example.throunndaytour.database.DatabaseDaytour;
import com.example.throunndaytour.hlutir.DayTour;

public class DayTourController {
    public static void addDayTour(DayTour dayTour) {
        DatabaseDaytour.createDayTour(dayTour);
    }
}
