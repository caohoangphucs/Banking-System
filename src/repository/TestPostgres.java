package repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class TestPostgres {
    public static void main(String[] args) {
        try (Connection conn = Connector.getConnection()) {
            if (conn != null) {
                System.out.println("Kết nối thành công!");

                // Thực hiện truy vấn
                String sql = "SELECT * FROM users";
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);

                // Duyệt kết quả
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    String email = rs.getString("email");

                    System.out.println("ID: " + id + ", Name: " + name + ", Email: " + email);
                }

                rs.close();
                stmt.close();
            } else {
                System.out.println("Không thể kết nối đến CSDL.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
