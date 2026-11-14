import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class RoomManagement extends JFrame {

    JTextField roomNumberField;
    JComboBox<String> roomTypeBox;
    JTextField priceField;
    JComboBox<String> statusBox;

    DefaultTableModel tableModel;
    JTable roomTable;

    public RoomManagement() {

        // ================= FRAME =================

        setTitle("Hotel Management System - Room Management");
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

        logo.setBounds(30, 20, 250, 100);

        header.add(logo);


        // ================= TITLE =================

        JLabel title = new JLabel(
                "ROOM MANAGEMENT"
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
                350, 45, 700, 45
        );

        header.add(title);


        // ================= ROOM NUMBER =================

        JLabel roomNumberLabel =
                new JLabel("Room Number");

        roomNumberLabel.setFont(
                new Font("Arial", Font.BOLD, 16)
        );

        roomNumberLabel.setBounds(
                180, 190, 120, 30
        );

        add(roomNumberLabel);


        roomNumberField = new JTextField();

        roomNumberField.setBounds(
                310, 190, 200, 35
        );

        add(roomNumberField);


        // ================= ROOM TYPE =================

        JLabel roomTypeLabel =
                new JLabel("Room Type");

        roomTypeLabel.setFont(
                new Font("Arial", Font.BOLD, 16)
        );

        roomTypeLabel.setBounds(
                600, 190, 120, 30
        );

        add(roomTypeLabel);


        roomTypeBox = new JComboBox<>(
                new String[]{
                        "Single",
                        "Double",
                        "Deluxe",
                        "Suite"
                }
        );

        roomTypeBox.setBounds(
                720, 190, 200, 35
        );

        add(roomTypeBox);


        // ================= PRICE =================

        JLabel priceLabel =
                new JLabel("Price");

        priceLabel.setFont(
                new Font("Arial", Font.BOLD, 16)
        );

        priceLabel.setBounds(
                180, 250, 120, 30
        );

        add(priceLabel);


        priceField = new JTextField();

        priceField.setBounds(
                310, 250, 200, 35
        );

        add(priceField);


        // ================= STATUS =================

        JLabel statusLabel =
                new JLabel("Status");

        statusLabel.setFont(
                new Font("Arial", Font.BOLD, 16)
        );

        statusLabel.setBounds(
                600, 250, 120, 30
        );

        add(statusLabel);


        statusBox = new JComboBox<>(
                new String[]{
                        "Available",
                        "Occupied"
                }
        );

        statusBox.setBounds(
                720, 250, 200, 35
        );

        add(statusBox);


        // ================= ADD BUTTON =================

        JButton addButton =
                new JButton("ADD ROOM");

        addButton.setBounds(
                250, 320, 150, 40
        );

        addButton.setBackground(
                new Color(0, 102, 204)
        );

        addButton.setForeground(Color.WHITE);

        add(addButton);


        // ================= UPDATE BUTTON =================

        JButton updateButton =
                new JButton("UPDATE");

        updateButton.setBounds(
                450, 320, 150, 40
        );

        updateButton.setBackground(
                new Color(255, 153, 0)
        );

        updateButton.setForeground(Color.WHITE);

        add(updateButton);


        // ================= DELETE BUTTON =================

        JButton deleteButton =
                new JButton("DELETE");

        deleteButton.setBounds(
                650, 320, 150, 40
        );

        deleteButton.setBackground(
                new Color(220, 53, 69)
        );

        deleteButton.setForeground(Color.WHITE);

        add(deleteButton);


        // ================= CLEAR BUTTON =================

        JButton clearButton =
                new JButton("CLEAR");

        clearButton.setBounds(
                850, 320, 150, 40
        );

        clearButton.setBackground(
                new Color(100, 100, 100)
        );

        clearButton.setForeground(Color.WHITE);

        add(clearButton);


        // ================= TABLE =================

        String[] columns = {
                "Room ID",
                "Room Number",
                "Room Type",
                "Price",
                "Status"
        };

        tableModel =
                new DefaultTableModel(columns, 0);

        roomTable =
                new JTable(tableModel);

        roomTable.setFont(
                new Font("Arial", Font.PLAIN, 14)
        );

        roomTable.setRowHeight(30);
        roomTable.removeColumn(
                roomTable.getColumnModel().getColumn(0)
        );


        JScrollPane scrollPane =
                new JScrollPane(roomTable);

        scrollPane.setBounds(
                200, 400, 1000, 200
        );

        add(scrollPane);
        // Load room records from MySQL
        loadRooms();


        // ================= ADD ROOM ACTION =================

        addButton.addActionListener(e -> {

            String number = roomNumberField.getText().trim();
            String type = roomTypeBox.getSelectedItem().toString();
            String status = statusBox.getSelectedItem().toString();

            if (number.isEmpty() || priceField.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(
                        this, "Please enter Room Number and Price!"
                );
                return;
            }

            try {

                double price = Double.parseDouble(priceField.getText().trim());

                if (price <= 0) {
                    JOptionPane.showMessageDialog(
                            this, "Price must be greater than zero!"
                    );
                    return;
                }

                boolean success = RoomDAO.addRoom(
                        number, type, price, status
                );

                if (success) {

                    loadRooms();
                    clearFields();

                    JOptionPane.showMessageDialog(
                            this, "Room Added Successfully!"
                    );

                } else {
                    JOptionPane.showMessageDialog(
                            this, "Room could not be added!"
                    );
                }

            } catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(
                        this, "Enter a valid price!"
                );
            }
        });




        // ================= DELETE ACTION =================

        deleteButton.addActionListener(e -> {

            int selectedRow =
                    roomTable.getSelectedRow();

            if (selectedRow == -1) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please select a room"
                );

            } else {

                tableModel.removeRow(
                        selectedRow
                );

                JOptionPane.showMessageDialog(
                        this,
                        "Room Deleted Successfully"
                );
            }
        });


        // ================= UPDATE ACTION =================

        updateButton.addActionListener(e -> {

            int selectedRow =
                    roomTable.getSelectedRow();

            if (selectedRow == -1) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please select a room"
                );

            } else {

                tableModel.setValueAt(
                        roomNumberField.getText(),
                        selectedRow,
                        0
                );

                tableModel.setValueAt(
                        roomTypeBox.getSelectedItem(),
                        selectedRow,
                        1
                );

                tableModel.setValueAt(
                        priceField.getText(),
                        selectedRow,
                        2
                );

                tableModel.setValueAt(
                        statusBox.getSelectedItem(),
                        selectedRow,
                        3
                );

                JOptionPane.showMessageDialog(
                        this,
                        "Room Updated Successfully"
                );
            }
        });


        // ================= TABLE CLICK =================

        roomTable.getSelectionModel()
                .addListSelectionListener(e -> {

                    int row =
                            roomTable.getSelectedRow();

                    if (row >= 0) {

                        roomNumberField.setText(
                                tableModel
                                        .getValueAt(row, 0)
                                        .toString()
                        );

                        roomTypeBox.setSelectedItem(
                                tableModel
                                        .getValueAt(row, 1)
                        );

                        priceField.setText(
                                tableModel
                                        .getValueAt(row, 2)
                                        .toString()
                        );

                        statusBox.setSelectedItem(
                                tableModel
                                        .getValueAt(row, 3)
                        );
                    }
                });


        // ================= CLEAR ACTION =================

        clearButton.addActionListener(e -> {

            clearFields();

            roomTable.clearSelection();

        });


        // ================= BACK BUTTON =================

        JButton backButton =
                new JButton("BACK TO DASHBOARD");

        backButton.setBounds(
                550, 630, 300, 45
        );

        backButton.setBackground(
                new Color(20, 60, 120)
        );

        backButton.setForeground(Color.WHITE);

        add(backButton);


        backButton.addActionListener(e -> {

            new Dashboard();

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
                0, 700, 1400, 60
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


        setVisible(true);
    }


    // ================= CLEAR FIELDS =================

    private void clearFields() {

        roomNumberField.setText("");

        priceField.setText("");

        roomTypeBox.setSelectedIndex(0);

        statusBox.setSelectedIndex(0);
    }


    // ================= MAIN =================

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new RoomManagement();
        });

    }
    private void loadRooms() {

        tableModel.setRowCount(0);

        java.util.List<Object[]> rooms =
                RoomDAO.getAllRooms();

        for (Object[] room : rooms) {
            tableModel.addRow(room);
        }

        System.out.println(
                "Total Rooms Loaded: " + rooms.size()
        );
    }
}