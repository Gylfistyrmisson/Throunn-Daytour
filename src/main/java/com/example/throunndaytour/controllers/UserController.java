package com.example.throunndaytour.controllers;
import com.example.throunndaytour.database.DatabaseDaytour;

public class UserController {
    public void createUser(String name,String email, String kennitala, String password) {
        DatabaseDaytour.createUser(name,email,kennitala,password);
    }
}
