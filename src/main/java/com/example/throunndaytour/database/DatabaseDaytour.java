package com.example.throunndaytour.database;
import java.sql.*;
public class DatabaseDaytour {
    private static Connection conn = null;
    public static void getConnection() throws ClassNotFoundException {
        if(conn != null) return;
        Class.forName("org.sqlite.JDBC");

        try{
            conn = DriverManager.getConnection("jdbc:sqlite:src/main/java/com/example/throunndaytour/database/daytour.db");
            conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            System.out.println("yesssssie!!!!!");
        }catch(SQLException e){
            System.err.println(e.getMessage());
        }

    }

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
}
