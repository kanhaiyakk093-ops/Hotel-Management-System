import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class RoomBooking extends JFrame {

    JTextField nameField;
    JTextField mobileField;
    JComboBox<String> roomTypeBox;
    JComboBox<String> roomNumberBox;
    JTextField daysField;

    DefaultTableModel tableModel;
    JTable bookingTable;

    public RoomBooking() {

        setTitle("Hotel Management System - Room Booking");
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
        JLabel title = new JLabel("ROOM BOOKING");
        title.setFont(new Font("Arial", Font.BOLD, 32));
        title.setForeground(new Color(20, 60, 120));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setBounds(350, 45, 700, 45);
        header.add(title);

        // Customer Name
        JLabel nameLabel = new JLabel("Customer Name");
        nameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        nameLabel.setBounds(180, 190, 130, 30);
        add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(320, 190, 220, 35);
        add(nameField);

        // Mobile Number
        JLabel mobileLabel = new JLabel("Mobile Number");
        mobileLabel.setFont(new Font("Arial", Font.BOLD, 16));
        mobileLabel.setBounds(650, 190, 130, 30);
        add(mobileLabel);

        mobileField = new JTextField();
        mobileField.setBounds(790, 190, 220, 35);
        add(mobileField);

        // Room Type
        JLabel roomTypeLabel = new JLabel("Room Type");
        roomTypeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        roomTypeLabel.setBounds(180, 250, 130, 30);
        add(roomTypeLabel);

        roomTypeBox = new JComboBox<>(
                new String[]{
                        "Single",
                        "Double",
                        "Deluxe",
                        "Suite"
                }
        );
        roomTypeBox.setBounds(320, 250, 220, 35);
        add(roomTypeBox);

// Room numbers will come from MySQL
        roomNumberBox = new JComboBox<>();

        roomNumberBox.setBounds(790, 250, 220, 35);
        add(roomNumberBox);

        roomNumberBox.addActionListener(
                e -> updateRoomType()
        );


        roomNumberBox.addActionListener(e -> updateRoomType());

// Room type will be selected automatically
        roomTypeBox.setEditable(false);


        // Number of Days
        JLabel daysLabel = new JLabel("Number of Days");
        daysLabel.setFont(new Font("Arial", Font.BOLD, 16));
        daysLabel.setBounds(180, 310, 130, 30);
        add(daysLabel);


        daysField = new JTextField();
        daysField.setBounds(320, 310, 220, 35);
        add(daysField);

        // Book Button
        JButton bookButton = new JButton("BOOK ROOM");
        bookButton.setFont(new Font("Arial", Font.BOLD, 15));
        bookButton.setBackground(new Color(0, 102, 204));
        bookButton.setForeground(Color.WHITE);
        bookButton.setFocusPainted(false);
        bookButton.setBounds(650, 310, 160, 40);
        add(bookButton);

        // Clear Button
        JButton clearButton = new JButton("CLEAR");
        clearButton.setFont(new Font("Arial", Font.BOLD, 15));
        clearButton.setBackground(new Color(100, 100, 100));
        clearButton.setForeground(Color.WHITE);
        clearButton.setFocusPainted(false);
        clearButton.setBounds(830, 310, 160, 40);
        add(clearButton);

        // Table
        String[] columns = {
                "Booking ID",
                "Customer Name",
                "Mobile",
                "Room Type",
                "Room Number",
                "Days"
        };

        tableModel = new DefaultTableModel(columns, 0);

        bookingTable = new JTable(tableModel);
        bookingTable.setFont(new Font("Arial", Font.PLAIN, 14));
        bookingTable.setRowHeight(30);

        // Hide Booking ID column
        bookingTable.removeColumn(
                bookingTable.getColumnModel().getColumn(0)
        );

        JScrollPane scrollPane = new JScrollPane(bookingTable);
        scrollPane.setBounds(150, 390, 1100, 180);
        add(scrollPane);
        loadAvailableRooms();
        loadBookings();


        bookButton.addActionListener(e -> {

            String name = nameField.getText().trim();
            String mobile = mobileField.getText().trim();
            String daysText = daysField.getText().trim();

            String roomNumber =
                    (String) roomNumberBox.getSelectedItem();

            if (name.isEmpty() || mobile.isEmpty()
                    || daysText.isEmpty() || roomNumber == null) {

                JOptionPane.showMessageDialog(
                        this, "Please fill all details and select a room!"
                );
                return;
            }

            if (!mobile.matches("[0-9]{10}")) {
                JOptionPane.showMessageDialog(
                        this, "Enter a valid 10-digit mobile number!"
                );
                return;
            }

            try {

                int days = Integer.parseInt(daysText);

                if (days <= 0) {
                    JOptionPane.showMessageDialog(
                            this, "Days must be greater than zero!"
                    );
                    return;
                }

                boolean success = BookingDAO.bookRoom(
                        name, mobile, roomNumber, days
                );

                if (success) {

                    loadBookings();
                    loadAvailableRooms();
                    clearFields();

                    JOptionPane.showMessageDialog(
                            this, "Room Booked Successfully!"
                    );

                } else {

                    JOptionPane.showMessageDialog(
                            this, "Room is unavailable or booking failed!"
                    );
                }

            } catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(
                        this, "Please enter a valid number of days!"
                );
            }
        });




        // Clear Action
        clearButton.addActionListener(e -> {
            clearFields();
        });

        // Back Button
        JButton backButton =
                new JButton("BACK TO USER DASHBOARD");

        backButton.setFont(
                new Font("Arial", Font.BOLD, 15)
        );
        backButton.setBackground(
                new Color(20, 60, 120)
        );
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButton.setBounds(520, 600, 360, 45);
        add(backButton);

        backButton.addActionListener(e -> {
            new UserDashboard();
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
    private void clearFields() {

        nameField.setText("");
        mobileField.setText("");
        daysField.setText("");

        if (roomNumberBox.getItemCount() > 0) {
            roomNumberBox.setSelectedIndex(0);
        }

        updateRoomType();
    }






    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new RoomBooking();


        });
    }
    // LOAD AVAILABLE ROOMS FROM MYSQL
    private void loadAvailableRooms() {

        roomNumberBox.removeAllItems();

        java.util.List<Object[]> rooms =
                BookingDAO.getAvailableRooms();

        for (Object[] room : rooms) {
            roomNumberBox.addItem(room[1].toString());
        }

        updateRoomType();
    }

    // UPDATE ROOM TYPE WHEN ROOM NUMBER CHANGES
    private void updateRoomType() {

        String selectedNumber =
                (String) roomNumberBox.getSelectedItem();

        if (selectedNumber == null) return;

        for (Object[] room : BookingDAO.getAvailableRooms()) {

            if (room[1].toString().equals(selectedNumber)) {

                roomTypeBox.setSelectedItem(room[2].toString());
                return;
            }
        }
    }

    // LOAD EXISTING BOOKINGS
    private void loadBookings() {

        tableModel.setRowCount(0);

        for (Object[] booking : BookingDAO.getAllBookings()) {
            tableModel.addRow(booking);
        }
    }
}