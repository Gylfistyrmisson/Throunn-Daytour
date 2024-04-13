package com.example.throunndaytour.users;

public abstract class User {
    private int id;
    private String name = null;
    private String email;
    private String phonenumber;
    private String kennitala;
    private String password;

    public User(int id, String name, String email, String phonenumber, String kennitala, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phonenumber = phonenumber;
        this.kennitala = kennitala;
        this.password = password;
    }
}
