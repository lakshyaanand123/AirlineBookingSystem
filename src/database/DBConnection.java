package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/airline_db";
    private static final String USERNAME = "root";

    // Replace with your MySQL password
    private static final String PASSWORD = "MyNewPassword123";

    private static Connection connection = null;

    public static Connection getConnection() {

        try {

            if (connection == null || connection.isClosed()) {

                connection = DriverManager.getConnection(
                        URL,
                        USERNAME,
                        PASSWORD);

                System.out.println("✅ Database Connected Successfully.");

            }

        } catch (SQLException e) {

            System.out.println("❌ Database Connection Failed.");
            e.printStackTrace();

        }

        return connection;
    }

}