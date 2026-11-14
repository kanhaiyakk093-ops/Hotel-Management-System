
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAO {

    // Verify username and password from MySQL
    public static boolean validateLogin(
            String username, String password) {

        String sql = "SELECT id FROM users " +
                "WHERE username = ? AND password = ?";

        try (Connection con = DBConnection.getConnection()) {

            if (con == null) {
                return false;
            }

            try (PreparedStatement ps =
                         con.prepareStatement(sql)) {

                ps.setString(1, username);
                ps.setString(2, password);

                try (ResultSet rs = ps.executeQuery()) {
                    return rs.next();
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Check whether username exists
    public static boolean userExists(String username) {

        String sql = "SELECT id FROM users WHERE username = ?";

        try (Connection con = DBConnection.getConnection()) {

            if (con == null) {
                return false;
            }

            try (PreparedStatement ps =
                         con.prepareStatement(sql)) {

                ps.setString(1, username);

                try (ResultSet rs = ps.executeQuery()) {
                    return rs.next();
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
