package com.example.throunndaytour.users;

public class User {
    private int id;
    private String name = null;
    private String email;
    private String kennitala;
    private String password;

    public User(int id, String name, String email, String kennitala, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.kennitala = kennitala;
        this.password = password;
    }
    public static String getEmail(User user) {
        return user.email;
    }
}
