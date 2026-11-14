import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.swing.*;
import java.awt.*;
import java.io.*;

public class frame extends JFrame {

    public frame() {

        // ================= FRAME SETTINGS =================

        setTitle("Hotel Management System");
        setSize(1400, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);


        // ================= POORNIMA IMAGE =================

        ImageIcon icon = new ImageIcon(
                getClass().getResource("/image/poornima.jpg")
        );

        Image image = icon.getImage();

        Image resizedImage = image.getScaledInstance(
                1350,
                200,
                Image.SCALE_SMOOTH
        );

        JLabel imageLabel = new JLabel(
                new ImageIcon(resizedImage)
        );

        imageLabel.setBounds(
                25,
                0,
                1350,
                200
        );

        add(imageLabel);


        // ================= TITLE =================

        JLabel titleLabel = new JLabel(
                "HOTEL MANAGEMENT SYSTEM"
        );

        titleLabel.setFont(
                new Font("Arial", Font.BOLD, 28)
        );

        titleLabel.setForeground(
                new Color(0, 102, 204)
        );

        titleLabel.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        titleLabel.setBounds(
                450,
                220,
                500,
                40
        );

        add(titleLabel);


        // ================= USERNAME =================

        JLabel usernameLabel = new JLabel("Username");

        usernameLabel.setFont(
                new Font("Arial", Font.BOLD, 16)
        );

        usernameLabel.setBounds(
                430,
                290,
                100,
                30
        );

        add(usernameLabel);


        JTextField usernameField = new JTextField();

        usernameField.setBounds(
                550,
                290,
                280,
                35
        );

        add(usernameField);


        // ================= PASSWORD =================

        JLabel passwordLabel = new JLabel("Password");

        passwordLabel.setFont(
                new Font("Arial", Font.BOLD, 16)
        );

        passwordLabel.setBounds(
                430,
                350,
                100,
                30
        );

        add(passwordLabel);


        JPasswordField passwordField =
                new JPasswordField();

        passwordField.setBounds(
                550,
                350,
                280,
                35
        );

        add(passwordField);


        // ================= LOGIN BUTTON =================

        JButton loginButton =
                new JButton("Login");

        loginButton.setBounds(
                640,
                430,
                120,
                40
        );

        loginButton.setFont(
                new Font("Arial", Font.BOLD, 15)
        );

        loginButton.setBackground(
                new Color(0, 102, 204)
        );

        loginButton.setForeground(Color.WHITE);

        loginButton.setFocusPainted(false);

        add(loginButton);


        // ================= FORGOT PASSWORD BUTTON =================

        JButton forgotButton =
                new JButton("Forgot Password?");

        forgotButton.setBounds(
                620,
                480,
                160,
                30
        );

        forgotButton.setFont(
                new Font("Arial", Font.PLAIN, 13)
        );

        forgotButton.setForeground(
                new Color(0, 102, 204)
        );

        forgotButton.setBackground(Color.WHITE);

        forgotButton.setBorderPainted(false);

        forgotButton.setFocusPainted(false);

        add(forgotButton);


        // ================= LOGIN ACTION =================


        loginButton.addActionListener(e -> {

            String username = usernameField.getText().trim();

            String password =
                    new String(passwordField.getPassword());

            if (username.isEmpty() || password.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please enter Username and Password!"
                );

                return;
            }

            // Check credentials from MySQL
            if (LoginDAO.validateLogin(username, password)) {

                JOptionPane.showMessageDialog(
                        this,
                        "Login Successful!"
                );

                // Admin Dashboard
                if (username.equalsIgnoreCase("admin")) {

                    new Dashboard();

                } else {

                    // User Dashboard
                    new UserDashboard();
                }

                dispose();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Invalid Username or Password!"
                );
            }
        });



        // ================= FORGOT PASSWORD ACTION =================


        forgotButton.addActionListener(e -> {

            String username = JOptionPane.showInputDialog(
                    this,
                    "Enter your Username:",
                    "Forgot Password",
                    JOptionPane.QUESTION_MESSAGE
            );

            if (username == null || username.trim().isEmpty()) {
                return;
            }

            if (LoginDAO.userExists(username.trim())) {

                JOptionPane.showMessageDialog(
                        this,
                        "Your account exists. Please contact the administrator to reset your password.",
                        "Password Recovery",
                        JOptionPane.INFORMATION_MESSAGE
                );

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Username not found!",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });



        // ================= PROFESSIONAL FOOTER =================

        JPanel footerPanel = new JPanel();

        footerPanel.setLayout(
                new BorderLayout()
        );

        footerPanel.setBackground(
                new Color(25, 25, 25)
        );

        footerPanel.setBounds(
                0,
                640,
                1400,
                60
        );


        JLabel footerLabel = new JLabel(
                "Designed & Developed by Kanhaiya"
        );

        footerLabel.setFont(
                new Font("Arial", Font.BOLD, 15)
        );

        footerLabel.setForeground(
                Color.WHITE
        );

        footerLabel.setHorizontalAlignment(
                SwingConstants.CENTER
        );


        footerPanel.add(
                footerLabel,
                BorderLayout.CENTER
        );


        add(footerPanel);


        // ================= SHOW FRAME =================

        setVisible(true);
    }


    // ================= MAIN METHOD =================

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new frame();
        });

    }
}