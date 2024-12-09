package com.example.exercise351;

import javafx.application.Application;
import javafx.stage.Stage;
import java.sql.*;

public class BatchUpdate extends Application {


    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/test_db";
        String username = "root";
        String password = "yourpassword";

        // SQL Insert query
        String insertQuery = "INSERT INTO test_table (name, email) VALUES (?, ?)";

        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement stmt = conn.prepareStatement(insertQuery)) {

            conn.setAutoCommit(false);  // Turn off auto-commit to improve performance

            for (int i = 1; i <= 1000; i++) {
                stmt.setString(1, "Name" + i);
                stmt.setString(2, "email" + i + "@example.com");

                stmt.addBatch();  // Add to batch
            }

            // Execute the batch
            stmt.executeBatch();
            conn.commit();  // Commit the transaction

            System.out.println("Inserted 1000 records with batch update.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage stage) throws Exception {

    }
}
