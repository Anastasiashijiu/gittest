package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SqlInjectionExample {

    public void doLogin(String user, String pass) {
        // Use prepared statements to avoid SQL injection
        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost/test?" + "user=myuser&password=mypass");
             PreparedStatement pstmt = conn.prepareStatement(
                     "SELECT * FROM users WHERE user = ? AND password = ?")) {

            pstmt.setString(1, user);
            pstmt.setString(2, pass);

            try (ResultSet rs = pstmt.executeQuery()) {
                // ...
            }

        } catch (SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        }
    }
    
}
