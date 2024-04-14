package com.example.throunndaytour.controllers;
import com.example.throunndaytour.users.Customer;
import com.example.throunndaytour.users.Seller;
import java.util.Objects;

public class UserController {
    private Seller[] sellers = {};
    private Customer[] customers = {};

    public int createUser(String tegund,int id, String name, String email, String phonenumber, String kennitala, String password) {
        if (Objects.equals(tegund, "Seller")) {
            Seller seller = new Seller(id, name, email, phonenumber, kennitala, password);
            int lengdSeller = sellers.length;
            Seller[] nyttSellers = new Seller[lengdSeller+1];
            for (int i = 0;i < lengdSeller;i++){
                nyttSellers[i] = sellers[i];
            }
            nyttSellers[lengdSeller] = seller;
            sellers = nyttSellers;
            return 1;
        } else if (Objects.equals(tegund, "Customer")) {
            Customer customer = new Customer(id, name, email, phonenumber, kennitala, password);
            int lengdCustomer = customers.length;
            Customer[] nyttCustomers = new Customer[lengdCustomer+1];
            for (int i = 0;i < lengdCustomer;i++){
                nyttCustomers[i] = customers[i];
            }
            nyttCustomers[lengdCustomer] = customer;
            customers = nyttCustomers;
            return 1;
        } else return 0;
    }
}
