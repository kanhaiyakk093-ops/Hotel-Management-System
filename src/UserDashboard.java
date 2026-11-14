import javax.swing.*;
import java.awt.*;

public class UserDashboard extends JFrame {

    public UserDashboard() {

        setTitle("Hotel Management System - User Dashboard");
        setSize(1400, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(new Color(245, 245, 245));

        // Header
        JPanel header = new JPanel();
        header.setLayout(null);
        header.setBackground(new Color(230, 240, 250));
        header.setBounds(0, 0, 1400, 150);
        add(header);

        // Poornima Image
        ImageIcon icon = new ImageIcon(
                getClass().getResource("/image/poornima.jpg")
        );

        Image image = icon.getImage();
        Image resizedImage = image.getScaledInstance(
                250, 100, Image.SCALE_SMOOTH
        );

        JLabel logo = new JLabel(
                new ImageIcon(resizedImage)
        );
        logo.setBounds(30, 20, 250, 100);
        header.add(logo);

        // Title
        JLabel title = new JLabel(
                "HOTEL MANAGEMENT SYSTEM"
        );
        title.setFont(new Font("Arial", Font.BOLD, 32));
        title.setForeground(new Color(20, 60, 120));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setBounds(350, 45, 700, 45);
        header.add(title);

        // Welcome
        JLabel welcome = new JLabel("Welcome, User");
        welcome.setFont(new Font("Arial", Font.BOLD, 18));
        welcome.setBounds(1150, 55, 200, 30);
        header.add(welcome);

        // Heading
        JLabel heading = new JLabel("------ USER DASHBOARD ------");
        heading.setFont(new Font("Arial", Font.BOLD, 24));
        heading.setHorizontalAlignment(SwingConstants.CENTER);
        heading.setBounds(450, 190, 500, 40);
        add(heading);

        // Room Booking Panel
        JPanel bookingPanel = new JPanel();
        bookingPanel.setLayout(null);
        bookingPanel.setBackground(Color.WHITE);
        bookingPanel.setBorder(
                BorderFactory.createLineBorder(
                        new Color(20, 60, 120), 2
                )
        );
        bookingPanel.setBounds(500, 260, 400, 220);
        add(bookingPanel);

        JLabel roomLabel = new JLabel("ROOM BOOKING");
        roomLabel.setFont(new Font("Arial", Font.BOLD, 22));
        roomLabel.setHorizontalAlignment(SwingConstants.CENTER);
        roomLabel.setBounds(50, 30, 300, 40);
        bookingPanel.add(roomLabel);

        JButton bookingButton =
                new JButton("BOOK ROOM");

        bookingButton.setFont(
                new Font("Arial", Font.BOLD, 17)
        );
        bookingButton.setBackground(
                new Color(0, 102, 204)
        );
        bookingButton.setForeground(Color.WHITE);
        bookingButton.setFocusPainted(false);
        bookingButton.setBounds(75, 100, 250, 50);
        bookingPanel.add(bookingButton);

        bookingButton.addActionListener(e -> {
            new RoomBooking();
            dispose();
        });

        // Logout
        JButton logoutButton =
                new JButton("LOGOUT");

        logoutButton.setFont(
                new Font("Arial", Font.BOLD, 15)
        );
        logoutButton.setBackground(
                new Color(0, 102, 204)
        );
        logoutButton.setForeground(Color.WHITE);
        logoutButton.setFocusPainted(false);
        logoutButton.setBounds(600, 530, 200, 45);
        add(logoutButton);

        logoutButton.addActionListener(e -> {
            new frame();
            dispose();
        });

        // Footer
        JPanel footer = new JPanel();
        footer.setBackground(new Color(35, 75, 130));
        footer.setLayout(new BorderLayout());
        footer.setBounds(0, 700, 1400, 60);

        JLabel footerText = new JLabel(
                "Designed & Developed by Kanhaiya"
        );
        footerText.setFont(
                new Font("Arial", Font.BOLD, 20)
        );
        footerText.setForeground(Color.WHITE);
        footerText.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        footer.add(
                footerText,
                BorderLayout.CENTER
        );

        add(footer);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new UserDashboard();
        });
    }
}