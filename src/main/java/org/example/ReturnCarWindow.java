package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReturnCarWindow extends JFrame {
    private JTextField txtCarID;

    public ReturnCarWindow() {
        initComponents();
    }

    private void initComponents() {
        setTitle("Return a Car");
        setSize(400, 150);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        mainPanel.add(new JLabel("Car ID to Return:"));
        txtCarID = new JTextField();
        mainPanel.add(txtCarID);

        JButton btnReturn = new JButton("Return Car");
        JButton btnCancel = new JButton("Cancel");

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(btnReturn);
        buttonPanel.add(btnCancel);

        add(mainPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        btnReturn.addActionListener(new ActionListener() {
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

        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        pack();
    }
}