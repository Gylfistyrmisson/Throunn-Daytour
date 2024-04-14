package com.example.throunndaytour.database;
import com.example.throunndaytour.hlutir.DayTour;
import com.example.throunndaytour.users.User;
import java.sql.*;

/**
    Connection
 */

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
                String q3 = "INSERT INTO user (name,id,email,kennitala,password) VALUES ('" + name + "'," + id + ",'" + email + "','" + kennitala + "','" + password + "');";
                PreparedStatement c = conn.prepareStatement(q3);
                c.executeUpdate();

                //Skilar true ef það var hægt að búa til user annars skilar false
                System.out.println("Success");
                return new User(id, name, email, kennitala, password);
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

    /**
     *  Daytour föll
     */

    //Create daytour


    public static DayTour createDayTour(String Name,int Price,int Duration,int[] Date,String Location) {
        //Fá connection
        try {
            getConnection();

            //Búa til daytour
            int id = getID();
            String q = "INSERT INTO daytour (name,id,price,duration,dateDay,dateMonth,dateYear,location,customerCNT,customerID,reviewCNT,reviewID) VALUES ('" + Name + "'," + id + "," + Price + "," + Duration +"," + Date[0] +"," + Date[1] + "," + Date[2] + ",'" + Location + "'," + 0 + "," + 0 + ",'','');";
            PreparedStatement statement = conn.prepareStatement(q);
            statement.executeUpdate();
            return new DayTour(id,Name,Price,Duration,Date,Location,0,new int[0],0,new int[0]);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //Fá fylki af customerID(hjálpar aðferð)
    public static int[] getCustomers (int customerCNT, String customers) {
        int[] cust = new int[customerCNT];
        int i = 0;
        StringBuilder s = new StringBuilder();
        for (int j = 0; j < customers.length(); j++){
            char a = customers.charAt(j);
            if (a == ','){
                cust[i] = Integer.parseInt(s.toString());
                s = new StringBuilder();
            } else {
                s.append(a);
            }
        }
        return cust;
    }

    //Fá fylki af reviewID(hjálpar aðferð)

    public static int[] getReviews (int reviewCNT, String reviews) {
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
            return new DayTour(daytourID,name,price,duration,date,location,0,customers,0,reviews);

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public DayTour[] searchDayTour(String search) {
        try {
            getConnection();
            //Finna hversu margar raðir eru
            String c = "SELECT COUNT(*) FROM daytour WHERE name LIKE '%" + search + "%';";
            PreparedStatement cc = conn.prepareStatement(c);
            ResultSet ccc = cc.executeQuery();
            long lengd = ccc.getLong(1);

            //Fara gegnum daytour töfluna og finna þar sem search er í nafninu, skilar svo fylki af daytours
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
                int[] date = {rs.getInt("dateDay"),rs.getInt("dateMonth"),rs.getInt("dateYear")};
                String location = rs.getString("location");
                String customerID = rs.getString("customerID");
                String reviewID = rs.getString("reviewID");
                int customerCNT = rs.getInt("customerCNT");
                int reviewCNT = rs.getInt("reviewCNT");
                int[] customers = getCustomers(customerCNT,rs.getString("customerID"));
                int[] reviews = getReviews(reviewCNT,rs.getString("reviewID"));
                fylki[i] = new DayTour(id,name,price,duration,date,location,customerCNT,customers,reviewCNT,reviews);
            }
            return fylki;
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void createDayTour(DayTour dayTour) {
    }

    public static void removeDayTour(int id) {
    }
}
