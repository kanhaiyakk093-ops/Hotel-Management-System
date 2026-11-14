
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoomDAO {

    // ADD ROOM
    public static boolean addRoom(
            String number, String type,
            double price, String status) {

        String sql = "INSERT INTO rooms " +
                "(room_number, room_type, price, status) " +
                "VALUES (?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            if (con == null) return false;

            ps.setString(1, number);
            ps.setString(2, type);
            ps.setDouble(3, price);
            ps.setString(4, status);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // FETCH ALL ROOMS
    public static List<Object[]> getAllRooms() {

        List<Object[]> rooms = new ArrayList<>();

        String sql = "SELECT room_id, room_number, " +
                "room_type, price, status FROM rooms";

        try (Connection con = DBConnection.getConnection()) {

            if (con == null) return rooms;

            try (PreparedStatement ps = con.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {

                    Object[] row = {
                            rs.getInt("room_id"),
                            rs.getString("room_number"),
                            rs.getString("room_type"),
                            rs.getDouble("price"),
                            rs.getString("status")
                    };

                    rooms.add(row);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rooms;
    }

    // UPDATE ROOM
    public static boolean updateRoom(
            int id, String number, String type,
            double price, String status) {

        String sql = "UPDATE rooms SET room_number = ?, " +
                "room_type = ?, price = ?, status = ? " +
                "WHERE room_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            if (con == null) return false;

            ps.setString(1, number);
            ps.setString(2, type);
            ps.setDouble(3, price);
            ps.setString(4, status);
            ps.setInt(5, id);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // DELETE ROOM
    public static boolean deleteRoom(int id) {

        String sql = "DELETE FROM rooms WHERE room_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            if (con == null) return false;

            ps.setInt(1, id);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
