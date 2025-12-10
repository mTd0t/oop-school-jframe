package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// inheritance
public class ReturnCarWindow extends JFrame {
    // encapsulation
    private JTextField txtCarID;

    // constructor
    public ReturnCarWindow() {
        initComponents();
    }

    // encapsulation
    private void initComponents() {
        setTitle("Return a Car");
        setSize(400, 150);
        setLocationRelativeTo(null);

        // composition
        JPanel mainPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        mainPanel.add(new JLabel("Car ID to Return:"));
        txtCarID = new JTextField();
        mainPanel.add(txtCarID);

        // composition
        JButton buttonReturn = new JButton("Return Car");
        JButton buttonCancel = new JButton("Cancel");

        // composition
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(buttonReturn);
        buttonPanel.add(buttonCancel);

        add(mainPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // observer pattern
        buttonReturn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int carID = Integer.parseInt(txtCarID.getText());
                    if (ProjectDB.returnCar(carID)) {
                        JOptionPane.showMessageDialog(null,
                                "Car returned successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "No active rental found for this car!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid Car ID!", "Error", JOptionPane.ERROR_MESSAGE);
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