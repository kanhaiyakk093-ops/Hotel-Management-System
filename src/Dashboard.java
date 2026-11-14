import javax.swing.*;
import java.awt.*;

public class Dashboard extends JFrame {

    public Dashboard() {

        // ================= FRAME =================

        setTitle("Hotel Management System - Dashboard");
        setSize(1400, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(new Color(245, 245, 245));


        // ================= HEADER =================

        JPanel header = new JPanel();
        header.setLayout(null);
        header.setBackground(new Color(230, 240, 250));
        header.setBounds(0, 0, 1400, 150);

        add(header);


        // ================= POORNIMA LOGO =================

        ImageIcon icon = new ImageIcon(
                getClass().getResource("/image/poornima.jpg")
        );

        Image image = icon.getImage();

        Image resizedImage = image.getScaledInstance(
                250,
                100,
                Image.SCALE_SMOOTH
        );

        JLabel logo = new JLabel(
                new ImageIcon(resizedImage)
        );

        logo.setBounds(
                30,
                20,
                250,
                100
        );

        header.add(logo);


        // ================= TITLE =================

        JLabel title = new JLabel(
                "HOTEL MANAGEMENT SYSTEM"
        );

        title.setFont(
                new Font("Arial", Font.BOLD, 32)
        );

        title.setForeground(
                new Color(20, 60, 120)
        );

        title.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        title.setBounds(
                350,
                45,
                700,
                45
        );

        header.add(title);


        // ================= WELCOME =================

        JLabel welcome = new JLabel(
                "Welcome, Admin"
        );

        welcome.setFont(
                new Font("Arial", Font.BOLD, 18)
        );

        welcome.setBounds(
                1150,
                55,
                200,
                30
        );

        header.add(welcome);


        // ================= SERVICE TITLE =================

        JLabel serviceTitle = new JLabel(
                "------ Welcome Admin ------"
        );

        serviceTitle.setFont(
                new Font("Arial", Font.BOLD, 24)
        );

        serviceTitle.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        serviceTitle.setBounds(
                500,
                190,
                400,
                40
        );

        add(serviceTitle);


        // ================= ROOM MANAGEMENT PANEL =================

        JPanel roomPanel = new JPanel();

        roomPanel.setLayout(null);

        roomPanel.setBackground(Color.WHITE);

        roomPanel.setBorder(
                BorderFactory.createLineBorder(
                        new Color(20, 60, 120),
                        2
                )
        );

        roomPanel.setBounds(
                500,
                260,
                400,
                250
        );

        add(roomPanel);


        // ================= ROOM IMAGE =================

        ImageIcon roomIcon = new ImageIcon(
                getClass().getResource("/image/room.png")
        );

        Image roomImage = roomIcon.getImage();

        Image resizedRoomImage =
                roomImage.getScaledInstance(
                        90,
                        90,
                        Image.SCALE_SMOOTH
                );

        JLabel roomImageLabel = new JLabel(
                new ImageIcon(resizedRoomImage)
        );

        roomImageLabel.setBounds(
                155,
                15,
                90,
                90
        );

        roomPanel.add(roomImageLabel);


        // ================= ROOM BUTTON =================

        JButton roomButton =
                new JButton("ROOM MANAGEMENT");

        roomButton.setFont(
                new Font("Arial", Font.BOLD, 17)
        );

        roomButton.setBackground(
                new Color(0, 102, 204)
        );

        roomButton.setForeground(Color.WHITE);

        roomButton.setFocusPainted(false);

        roomButton.setBounds(
                75,
                135,
                250,
                50
        );

        roomPanel.add(roomButton);


        // ================= ROOM BUTTON ACTION =================

        roomButton.addActionListener(e -> {

            new RoomManagement();
            dispose();

        });


        // ================= LOGOUT =================

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

        logoutButton.setBounds(
                600,
                550,
                200,
                45
        );

        add(logoutButton);


        // ================= LOGOUT ACTION =================

        logoutButton.addActionListener(e -> {

            new frame();
            dispose();

        });


        // ================= FOOTER =================

        JPanel footer = new JPanel();

        footer.setBackground(
                new Color(35, 75, 130)
        );

        footer.setLayout(
                new BorderLayout()
        );

        footer.setBounds(
                0,
                700,
                1400,
                60
        );

        JLabel footerText =
                new JLabel(
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


        // ================= SHOW FRAME =================

        setVisible(true);
    }


    // ================= MAIN =================

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new Dashboard();
        });

    }
}