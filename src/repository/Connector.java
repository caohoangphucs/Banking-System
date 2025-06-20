package repository;
import org.postgresql.Driver;
import java.sql.*;
public class Connector {
    private static final String URL = "jdbc:postgresql://localhost:5432/banking_db";
    private static final String USER = "banking_user";
    private static final String PASSWORD = "1702";
    public static Connection getConnection() {
            try {
                return DriverManager.getConnection(URL, USER, PASSWORD);
            } catch (SQLException e) {
                System.err.println("Kết nối thất bại: " + e.getMessage());
                return null;
            }

}
    }

