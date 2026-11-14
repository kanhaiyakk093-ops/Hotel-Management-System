
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    // MySQL Database URL
    private static final String URL =
            "jdbc:mysql://localhost:3306/hotel_management";

    // MySQL Username
    private static final String USER = "root";

    // Enter your actual MySQL root password
    private static final String PASSWORD = "MyNewPass#2026";

    // Method to connect Java with MySQL
    public static Connection getConnection() {

        Connection con = null;

        try {

            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish database connection
            con = DriverManager.getConnection(
                    URL, USER, PASSWORD
            );

            System.out.println("MySQL Connected Successfully!");

        } catch (ClassNotFoundException e) {

            System.out.println("MySQL JDBC Driver Not Found!");
            e.printStackTrace();

        } catch (SQLException e) {

            System.out.println("Database Connection Failed!");
            e.printStackTrace();
        }

        return con;
    }

    // Test database connection
    public static void main(String[] args) {

        Connection con = getConnection();

        if (con != null) {
            System.out.println("Database is Working!");

            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } else {
            System.out.println("Database Not Connected!");
        }
    }
}
