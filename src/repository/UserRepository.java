package  repository;
import java.sql.*;
import dto.request.*;
import dto.db.UserDto;

import javax.swing.plaf.synth.SynthTextAreaUI;

public class UserRepository {
    private static final UserRepository instance = new UserRepository();
    private final Connection conn;
    private UserRepository(){
        conn = Connector.getConnection();
    }

    public static UserRepository getInstance() {
        return instance;
    }
    public boolean save(UserDto user) throws SQLException {
        String sql = "INSERT INTO users (name, age, address, email) values (?, ?, ?, ?)";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, user.getName());
            statement.setInt(2, user.getAge());
            statement.setString(3, user.getAddress());
            statement.setString(4, user.getEmail());
            return statement.executeUpdate() > 0;
        }
    }
    private UserDto mapDto(ResultSet result) throws SQLException {
        return new UserDto(
                String.valueOf(result.getInt("id")),
                result.getInt("age"),
                result.getString("name"),
                result.getString("address"),
                result.getString("email"));
    }
}
