package com.example.emae;

import java.sql.*;

public class BookingDataDAO {
    public void updateBookingData(BookingData bookingData) {
        System.out.println("updateBookingData method called");
        String DB_URL = "jdbc:mysql://localhost:13306/kyrsach";
        String USER = "javafxTest";
        String PASS = "changeme";

        String sql = "UPDATE booking SET dv = ? WHERE idB = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDate(1, new java.sql.Date(bookingData.getDv().getTime()));
            pstmt.setInt(2, bookingData.getIdB());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}