package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtils {
    private static final String DRIVER_PATH = "com.mysql.cj.jdbc.Driver";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Dinnu*91004";
    private static final String JDBC_URL = "jdbc:mysql://localhost:3305/student_database?useSSL=false";

    public static Connection getConnection() {
        Connection connection = null;

        try {
            Class.forName(DRIVER_PATH);

            connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException e) {
            System.out.println("Unable to load the driver");
            System.out.println(e);
        } catch (SQLException e) {
            System.out.println("Unable to establish the connection");
            System.out.println(e);
        }
        return connection;
    }
}
