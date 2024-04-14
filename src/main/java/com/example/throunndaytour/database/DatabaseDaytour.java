package com.example.throunndaytour.database;
import com.example.throunndaytour.hlutir.DayTour;
import com.example.throunndaytour.users.User;

import java.sql.*;
public class DatabaseDaytour {
    private static Connection conn = null;
    public static void getConnection() throws ClassNotFoundException {
        if(conn != null) return;
        Class.forName("org.sqlite.JDBC");

        try{
            conn = DriverManager.getConnection("jdbc:sqlite:src/main/java/com/example/throunndaytour/database/daytour.db");
            conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
        }catch(SQLException e){
            System.err.println(e.getMessage());
        }

    }

    //Býr til nýjan user(kannski að klasinn skili id?)
    public static boolean createUser(String name,String email, String kennitala, String password) {
        try {
            //Fá connection
            getConnection();

            //Checka hvort það sé user með sama email
            String til = "SELECT * FROM user WHERE email == '" + email + "';";
            PreparedStatement t = conn.prepareStatement(til);
            ResultSet till = t.executeQuery();

            if (!till.next()) {
                //Fá value úr idGenerator töflunni

                String q1 = "SELECT value FROM idGenerator";
                PreparedStatement iddd = conn.prepareStatement(q1);
                ResultSet idd = iddd.executeQuery();
                int id = idd.getInt("value");

                //Updatea value í idGenerator töflunni
                String q2 = "UPDATE idGenerator SET value =" + (id + 1) + ";";
                PreparedStatement update = conn.prepareStatement(q2);
                update.executeUpdate();

                //Búa til nýjan user í user töflunni
                String q3 = "INSERT INTO user (name,id,email,kennitala,password) VALUES ('" + name + "'," + id + ",'" + email + "','" + kennitala + "','" + password + "');";
                PreparedStatement c = conn.prepareStatement(q3);
                c.executeUpdate();

                //Skilar true ef það var hægt að búa til user annars skilar false
                System.out.println("Success");
                return true;
                }
            else return false;
            } catch(ClassNotFoundException | SQLException e){
                throw new RuntimeException(e);
            }
    }

    //Skilar user með id
    public static User getUser(int userid) {
        try {
            DatabaseDaytour.getConnection();
            String q = "SELECT * FROM user WHERE id == " + userid + ";";
            System.out.println(q);
            PreparedStatement q1 = conn.prepareStatement(q);
            ResultSet q2 = q1.executeQuery();

            //Ef ekkert finnst þá skila null
            if (!q2.next()) {
                return null;
            }
            //Annars skila user
            else {
                String name = q2.getString("name");
                int id = q2.getInt("id");
                String email = q2.getString("email");
                String kennitala = q2.getString("kennitala");
                String password = q2.getString("password");
                User user = new User(id, name, email, kennitala, password);
                return user;
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static User authenticateUser(String email, String password) {
        try {
            getConnection();
            String query = "SELECT * FROM user WHERE email = ? AND password = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, email);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String kennitala = rs.getString("kennitala");
                User user = new User(id, name, email, kennitala, password);
                return user;
            }
            return null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    //Þessi er til sýnis
    public static void hallo() {
        try {
            DatabaseDaytour.getConnection();
            String sql = "SELECT name FROM user WHERE 0 < id;";
            PreparedStatement psmt = conn.prepareStatement(sql);
            ResultSet rs =  psmt.executeQuery();
            System.out.println(rs.getString("name"));
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void createDayTour(DayTour dayTour) {
    }
}
