package com.example.throunndaytour.database;
import com.example.throunndaytour.hlutir.Booking;
import com.example.throunndaytour.hlutir.DayTour;
import com.example.throunndaytour.hlutir.Review;
import com.example.throunndaytour.users.User;
import java.sql.*;
import java.util.Arrays;

/**
    Connection
 */

public class DatabaseDaytour {
    private User[] users = new User[0];


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

    /**
     * Fá id frá idGenerator
     */

    public static int getID() throws ClassNotFoundException, SQLException {
        getConnection();
        String q1 = "SELECT value FROM idGenerator";
        PreparedStatement iddd = conn.prepareStatement(q1);
        ResultSet idd = iddd.executeQuery();
        int id = idd.getInt("value");

        //Updatea value í idGenerator töflunni
        String q2 = "UPDATE idGenerator SET value =" + (id + 1) + ";";
        PreparedStatement update = conn.prepareStatement(q2);
        update.executeUpdate();
        return id;
        }

    /**
     * User föll
     * @param name
     * @param email
     * @param kennitala
     * @param password
     * @return
     */

    //Býr til nýjan user(kannski að klasinn skili id?)
    public static User createUser(String name,String email, String kennitala, String password) {
        try {
            //Fá connection
            getConnection();

            //Checka hvort það sé user með sama email
            String til = "SELECT * FROM user WHERE email == '" + email + "';";
            PreparedStatement t = conn.prepareStatement(til);
            ResultSet till = t.executeQuery();

            if (!till.next()) {
                //Fá value úr idGenerator töflunni
                int id = getID();

                //Búa til nýjan user í user töflunni
                String q3 = "INSERT INTO user (name,id,email,kennitala,password,daytourCNT,daytours) VALUES ('" + name + "'," + id + ",'" + email + "','" + kennitala + "','" + password + "'," + 0 + ",'');";
                PreparedStatement c = conn.prepareStatement(q3);
                c.executeUpdate();

                //Skilar true ef það var hægt að búa til user annars skilar false
                System.out.println("Success");
                return new User(id, name, email, kennitala, password,0,new int[]{});
                }
            else return null;
            } catch(ClassNotFoundException | SQLException e){
                throw new RuntimeException(e);
            }
    }

    //Skilar user með id
    public static User getUser(int userid) {
        try {
            getConnection();
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
                int dayTourCNT = q2.getInt("daytourCNT");
                int[] dayTours = new int[dayTourCNT];
                String daytours = q2.getString("daytours");
                StringBuilder daytour = new StringBuilder();
                int j = 0;
                for (int i = 0;i < daytours.length();i++) {
                    if (daytours.charAt(i) != ",".charAt(0)) {
                        daytour.append(daytours.charAt(i));
                    } else {
                        dayTours[j] = Integer.parseInt(String.valueOf(daytour));
                    }
                }
                return new User(id, name, email, kennitala, password,dayTourCNT,dayTours);
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
                int dayTourCNT = rs.getInt("daytourCNT");
                int[] dayTours = new int[dayTourCNT];
                String daytours = rs.getString("daytours");
                StringBuilder daytour = new StringBuilder();
                int j = 0;
                for (int i = 0;i < daytours.length();i++) {
                    if (daytours.charAt(i) != ",".charAt(0)) {
                        daytour.append(daytours.charAt(i));
                    } else {
                        dayTours[j] = Integer.parseInt(String.valueOf(daytour));
                    }
                }
                return new User(id, name, email, kennitala, password,dayTourCNT,dayTours);
            }
            return null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



    /**
     *  Daytour föll
     */

    //Create daytour (testað og virkar)


    public static DayTour createDayTour(String Name,int Price,int Duration,int[] Date,String Location,String description) {
        //Fá connection
        try {
            getConnection();

            //Búa til daytour
            int id = getID();
            String q = "INSERT INTO daytour (name,id,price,duration,dateDay,dateMonth,dateYear,location,customerCNT,customerID,reviewCNT,reviewID,description) VALUES ('" + Name + "'," + id + "," + Price + "," + Duration +"," + Date[0] +"," + Date[1] + "," + Date[2] + ",'" + Location + "'," + 0 + ",''," + 0 + ",'','" + description + "');";
            PreparedStatement statement = conn.prepareStatement(q);
            statement.executeUpdate();
            System.out.println("yahoo");
            return new DayTour(id,Name,Price,Duration,Date,Location,0,new int[0],0,new int[0],description);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void deleteDayTour(int daytourID) {
        try {
            getConnection();
            String sql = "DELETE FROM daytour WHERE id = ?;";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, daytourID);

            // Execute the update
            int affectedRows = statement.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("DayTour deleted successfully.");
            } else {
                System.out.println("No DayTour found with ID: " + daytourID);
            }
        } catch (SQLException e) {
            System.err.println("SQL error during the delete operation: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println("Database connection problem: " + e.getMessage());
        }
    }

    //Fá fylki af customerID(hjálpar aðferð)
    public static int[] getCustomers (int customerCNT, String customers) {
        int[] cust = new int[customerCNT];
        int i = 0;
        String s = "";
        System.out.println(customers.length());
        for (int j = 0; j < customers.length(); j++){
            System.out.println(s);
            if (customers.charAt(j) == ','){
                cust[i] = Integer.parseInt(s);
                i++;
                s = "";
            } else {
                s += customers.charAt(j);
            }
        }
        return cust;
    }

    //Fá fylki af reviewID(hjálpar aðferð)

    public static int[] getReviews (int reviewCNT, String reviews) {
        if (reviewCNT == 0) {return new int[]{};}
        int[] cust = new int[reviewCNT];
        int i = 0;
        StringBuilder s = new StringBuilder();
        for (int j = 0; j < reviews.length(); j++){
            char a = reviews.charAt(j);
            if (a == ','){
                cust[i] = Integer.parseInt(s.toString());
                s = new StringBuilder();
            } else {
                s.append(a);
            }
        }
        return cust;
    }

    //Get daytour
    public static DayTour getDayTour(int daytourID) {
        try {
            getConnection();
            String q = "SELECT * FROM daytour WHERE id == " + daytourID;
            PreparedStatement statement = conn.prepareStatement(q);
            ResultSet rs = statement.executeQuery();
            String name = rs.getString("name");
            int price = rs.getInt("price");
            int duration = rs.getInt("duration");
            int[] date = {rs.getInt("dateDay"),rs.getInt("dateMonth"),rs.getInt("dateYear")};
            String location = rs.getString("location");
            String customerID = rs.getString("customerID");
            String reviewID = rs.getString("reviewID");
            int customerCNT = rs.getInt("customerCNT");
            int reviewCNT = rs.getInt("reviewCNT");
            int[] customers = getCustomers(customerCNT,rs.getString("customerID"));
            int[] reviews = getReviews(reviewCNT,rs.getString("reviewID"));
            String description = rs.getString("description");
            return new DayTour(daytourID,name,price,duration,date,location,0,customers,0,reviews,description);

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static DayTour[] searchDayTour(String search) {
        try {
            getConnection();
            // Find out how many rows there are
            String c = "SELECT COUNT(*) FROM daytour WHERE name LIKE '%" + search + "%';";
            PreparedStatement cc = conn.prepareStatement(c);
            ResultSet ccc = cc.executeQuery();
            ccc.next(); // Move to the first row to retrieve the count
            long lengd = ccc.getLong(1);

            // Go through the daytour table and find where search is in the name, then return an array of daytours
            String q = "SELECT * FROM daytour WHERE name LIKE '%" + search + "%';";
            PreparedStatement smt = conn.prepareStatement(q);
            ResultSet rs = smt.executeQuery();
            DayTour[] fylki = new DayTour[(int) lengd];
            int i = 0;
            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int price = rs.getInt("price");
                int duration = rs.getInt("duration");
                int[] date = {rs.getInt("dateDay"), rs.getInt("dateMonth"), rs.getInt("dateYear")};
                String location = rs.getString("location");
                String customerID = rs.getString("customerID");
                String reviewID = rs.getString("reviewID");
                int customerCNT = rs.getInt("customerCNT");
                int reviewCNT = rs.getInt("reviewCNT");

                // Check for null or empty customerID and reviewID before processing
                int[] customers = (customerID != null && !customerID.isEmpty()) ? getCustomers(customerCNT, customerID) : null;
                int[] reviews = (reviewID != null && !reviewID.isEmpty()) ? getReviews(reviewCNT, reviewID) : null;
                String description = rs.getString("description");

                fylki[i] = new DayTour(id, name, price, duration, date, location, customerCNT, customers, reviewCNT, reviews,description);
                i++; // Increment index after adding each tour to the array
            }
            System.out.println(fylki.length);
            return fylki;
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void addBooking(int daytourID,int userID) {
        try {
            getConnection();

            //Update the daytour database
            String q1 = "SELECT customerCNT,customerID FROM daytour WHERE id == " + daytourID + ";";
            PreparedStatement statement = conn.prepareStatement(q1);
            ResultSet rs1 = statement.executeQuery();
            int customerCNT = rs1.getInt("customerCNT");
            String customerID = rs1.getString("customerID");
            customerCNT += 1;
            customerID += userID + ",";
            rs1.close();
            String q2 = "UPDATE daytour SET customerCNT = " + customerCNT + ",customerID = '" + customerID + "' WHERE id == " + daytourID + ";";
            PreparedStatement statement1 = conn.prepareStatement(q2);
            statement1.executeUpdate();

            //Update the Daytour object
            String q3 = "SELECT daytourCNT,daytours FROM user WHERE id == " + userID + ";";
            PreparedStatement statement2 = conn.prepareStatement(q3);
            ResultSet rs2 = statement2.executeQuery();
            int daytourCNT = rs2.getInt("daytourCNT");
            String daytours = rs2.getString("daytours");
            daytourCNT += 1;
            daytours += daytourID + ",";

            String q4 = "UPDATE user SET daytourCNT = " + daytourCNT + ",daytours = '" + daytours + "' WHERE id == " + userID + ";";
            PreparedStatement statement3 = conn.prepareStatement(q4);
            statement3.executeUpdate();
            rs2.close();

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Booking aðgerðir
     */

    //Cancel booking
    public static void cancelBooking(int daytourID,int userID) {
        try {
            getConnection();

            //Update the daytour database
            String q1 = "SELECT customerCNT,customerID FROM daytour WHERE id == " + daytourID + ";";
            PreparedStatement statement = conn.prepareStatement(q1);
            ResultSet rs1 = statement.executeQuery();
            int customerCNT = rs1.getInt("customerCNT");
            String customerID = rs1.getString("customerID");
            StringBuilder customerIDnytt = new StringBuilder();
            String p = "";

            for (int i = 0; i < customerID.length();i++) {
                System.out.println(p);
                if (customerID.charAt(i) == ',') {
                    if (userID != Integer.parseInt(p)) {
                        customerIDnytt.append(p).append(",");
                    }
                    p = "";
                } else {
                    p += customerID.charAt(i);
                }
            }

            String q2 = "UPDATE daytour SET customerCNT = " + (customerCNT - 1) + ",customerID = '" + customerIDnytt + "' WHERE id == " + daytourID + ";";
            PreparedStatement statement1 = conn.prepareStatement(q2);
            statement1.executeUpdate();

            //Update the user object
            String q3 = "SELECT daytourCNT,daytours FROM user WHERE id == " + userID + ";";
            PreparedStatement statement2 = conn.prepareStatement(q3);
            ResultSet rs2 = statement2.executeQuery();
            int daytourCNT = rs2.getInt("daytourCNT");
            String daytours = rs2.getString("daytours");

            StringBuilder daytoursnytt = new StringBuilder();
            String q = "";
            for (int i = 0; i < daytours.length();i++) {
                if (daytours.charAt(i) == ',') {
                    if (daytourID != Integer.parseInt(q)) {
                        daytoursnytt.append(q).append(",");
                    }
                    q = "";
                }else {
                    q += daytours.charAt(i);
                }
            }

            String q4 = "UPDATE user SET daytourCNT = " + (daytourCNT - 1) + ",daytours = '" + daytoursnytt + "' WHERE id == " + userID + ";";
            PreparedStatement statement3 = conn.prepareStatement(q4);
            statement3.executeUpdate();
            rs1.close();
            rs2.close();

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Review aðgerðir
     */

    public static Review addReview(String title, String text, int rating, String username, int daytourID) {
        try {
            getConnection();

            //Insert review into review table in db
            int id = getID();
            String q1 = "INSERT INTO review (id,title,text,rating,username) VALUES(" + id + ",'" + title + "','" + text + "'," + rating + ",'" + username + "');";
            PreparedStatement statement1 = conn.prepareStatement(q1);
            statement1.executeUpdate();

            //Update daytour table with review
            String q2 = "SELECT reviewCNT,reviewID FROM daytour WHERE id == " + daytourID + ";";
            PreparedStatement statement2 = conn.prepareStatement(q2);
            ResultSet rs2 = statement2.executeQuery();
            int reviewCNT = rs2.getInt("reviewCNT");
            String reviewID = rs2.getString("reviewID");

            StringBuilder reviewIDnytt = new StringBuilder();
            String q = "";
            for (int i = 0; i < reviewID.length();i++) {
                if (reviewID.charAt(i) == ',') {
                    if (daytourID != Integer.parseInt(q)) {
                        reviewIDnytt.append(q).append(",");
                    }
                    q = "";
                }else {
                    q += reviewID.charAt(i);
                }
            }
            reviewIDnytt.append(id).append(",;");
            String q3 = "UPDATE daytour SET reviewCNT = " + (reviewCNT + 1) + ",reviewID = '" + reviewIDnytt + "' WHERE id == " + daytourID + ";";
            PreparedStatement statement3 = conn.prepareStatement(q3);
            statement3.executeUpdate();
            rs2.close();
            //Return review object
            return new Review(id,title,text,rating,username);

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Review getReview(int reviewID) {
        try {
            getConnection();

            //Fetch review from database
            String q = "SELECT * FROM review WHERE id == " + reviewID + ";";
            PreparedStatement statement = conn.prepareStatement(q);
            ResultSet rs = statement.executeQuery();

            String title = rs.getString("title");
            String text = rs.getString("text");
            int rating = rs.getInt("rating");
            String username = rs.getString("username");

            return new Review(reviewID,title,text,rating,username);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
