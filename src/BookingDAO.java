
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookingDAO {

    // GET AVAILABLE ROOMS
    public static List<Object[]> getAvailableRooms() {

        List<Object[]> rooms = new ArrayList<>();

        String sql = "SELECT room_id, room_number, room_type, price " +
                "FROM rooms WHERE status = 'Available'";

        try (Connection con = DBConnection.getConnection()) {

            if (con == null) return rooms;

            try (PreparedStatement ps = con.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {

                    rooms.add(new Object[]{
                            rs.getInt("room_id"),
                            rs.getString("room_number"),
                            rs.getString("room_type"),
                            rs.getDouble("price")
                    });
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rooms;
    }

    // BOOK ROOM AND UPDATE ROOM STATUS
    public static boolean bookRoom(
            String name, String mobile,
            String roomNumber, int days) {

        String findRoom =
                "SELECT room_id FROM rooms " +
                        "WHERE room_number = ? AND status = 'Available' FOR UPDATE";

        String insertBooking =
                "INSERT INTO bookings " +
                        "(customer_name, mobile, room_id, days) " +
                        "VALUES (?, ?, ?, ?)";

        String updateRoom =
                "UPDATE rooms SET status = 'Booked' WHERE room_id = ?";

        try (Connection con = DBConnection.getConnection()) {

            if (con == null) return false;

            con.setAutoCommit(false);

            try {
                int roomId;

                // Check room availability
                try (PreparedStatement ps =
                             con.prepareStatement(findRoom)) {

                    ps.setString(1, roomNumber);

                    try (ResultSet rs = ps.executeQuery()) {

                        if (!rs.next()) {
                            con.rollback();
                            return false;
                        }

                        roomId = rs.getInt("room_id");
                    }
                }

                // Save booking details
                try (PreparedStatement ps =
                             con.prepareStatement(insertBooking)) {

                    ps.setString(1, name);
                    ps.setString(2, mobile);
                    ps.setInt(3, roomId);
                    ps.setInt(4, days);

                    ps.executeUpdate();
                }

                // Mark room as booked
                try (PreparedStatement ps =
                             con.prepareStatement(updateRoom)) {

                    ps.setInt(1, roomId);
                    ps.executeUpdate();
                }

                con.commit();
                return true;

            } catch (SQLException e) {
                con.rollback();
                throw e;

            } finally {
                con.setAutoCommit(true);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // LOAD BOOKING HISTORY
    public static List<Object[]> getAllBookings() {

        List<Object[]> bookings = new ArrayList<>();

        String sql =
                "SELECT b.booking_id, b.customer_name, b.mobile, " +
                        "r.room_type, r.room_number, b.days " +
                        "FROM bookings b " +
                        "JOIN rooms r ON b.room_id = r.room_id " +
                        "ORDER BY b.booking_id DESC";

        try (Connection con = DBConnection.getConnection()) {

            if (con == null) return bookings;

            try (PreparedStatement ps = con.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {

                    bookings.add(new Object[]{
                            rs.getInt("booking_id"),
                            rs.getString("customer_name"),
                            rs.getString("mobile"),
                            rs.getString("room_type"),
                            rs.getString("room_number"),
                            rs.getInt("days")
                    });
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bookings;
    }
}
