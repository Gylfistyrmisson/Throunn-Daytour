package com.example.throunndaytour.controllers;
import com.example.throunndaytour.database.DatabaseDaytour;
import com.example.throunndaytour.users.Customer;
import com.example.throunndaytour.users.Seller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Objects;

public class UserController {
    public void createUser(String name,String email, String kennitala, String password) {
        DatabaseDaytour.createUser(name,email,kennitala,password);
    }
}
