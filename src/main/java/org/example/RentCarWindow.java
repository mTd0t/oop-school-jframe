package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

// inheritance
public class RentCarWindow extends JFrame {
    // encapsulation
    private JTextField txtCarID;
    private JTextField txtRenterName;
    private JTextField txtRenterPhone;
    private JTextField txtDuration;

    // constructor
    public RentCarWindow() {
        initComponents();
    }

    // encapsulation
    private void initComponents() {
        setTitle("Rent a Car");
        setSize(500, 400);
        setLocationRelativeTo(null);

        // composition
        JPanel mainPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        mainPanel.add(new JLabel("Car ID:"));
        txtCarID = new JTextField();
        mainPanel.add(txtCarID);

        mainPanel.add(new JLabel("Renter Name:"));
        txtRenterName = new JTextField();
        mainPanel.add(txtRenterName);

        mainPanel.add(new JLabel("Renter Phone:"));
        txtRenterPhone = new JTextField();
        mainPanel.add(txtRenterPhone);

        mainPanel.add(new JLabel("Duration (days):"));
        txtDuration = new JTextField();
        mainPanel.add(txtDuration);

        // composition
        JButton buttonRent = new JButton("Rent Car");
        JButton buttonCancel = new JButton("Cancel");

        // composition
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(buttonRent);
        buttonPanel.add(buttonCancel);

        add(mainPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // observer pattern
        buttonRent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int carID = Integer.parseInt(txtCarID.getText());
                    String name = txtRenterName.getText();
                    String phone = txtRenterPhone.getText();
                    int duration = Integer.parseInt(txtDuration.getText());

                    if (name.isEmpty() || phone.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Please fill all fields", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    // composition
                    Car car = ProjectDB.findCarByID(carID);
                    if (car == null) {
                        JOptionPane.showMessageDialog(null, "Car ID not found!", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    if (car.isRented()) {
                        int choice = JOptionPane.showConfirmDialog(null,
                                "Car is rented. Add to pending queue?", "Car Unavailable",
                                JOptionPane.YES_NO_OPTION);
                        if (choice == JOptionPane.YES_OPTION) {
                            ProjectDB.addToPendingQueue(name, phone, carID, duration);
                            JOptionPane.showMessageDialog(null,
                                    "Added to queue. You will be notified when available.",
                                    "Queue Added", JOptionPane.INFORMATION_MESSAGE);
                            dispose();
                        }
                        return;
                    }

                    if (ProjectDB.rentCar(carID, name, phone, duration)) {
                        JOptionPane.showMessageDialog(null,
                                "Car rented successfully! Return by: " +
                                        LocalDate.now().plusDays(duration).format(DateTimeFormatter.ofPattern("MMM dd, yyyy")),
                                "Success", JOptionPane.INFORMATION_MESSAGE);
                        dispose();
                    }

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid input format!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // observer pattern
        buttonCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        pack();
    }
}