package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// inheritance
public class EditCars extends JFrame {
    // constructor
    public EditCars() {
        initComponents();
    }

    // encapsulation
    private void initComponents() {
        setTitle("Edit Car Details");
        setSize(900, 500);
        setLocationRelativeTo(null);

        // composition
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // composition
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton buttonEdit = new JButton("Edit Car");
        JButton buttonBack = new JButton("Cancel");

        JTextField txtCarID = new JTextField(15);
        buttonPanel.add(new JLabel("Car ID:"));
        buttonPanel.add(txtCarID);
        buttonPanel.add(buttonEdit);
        buttonPanel.add(buttonBack);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(mainPanel);

        pack();

        // observer pattern
        buttonEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int carID = Integer.parseInt(txtCarID.getText());
                editCar(carID);
                ProjectDB.loadCars();
            }
        });
        // observer pattern
        buttonBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    // encapsulation
    public void editCar(int carID) {
        for (int i = 0; i < ProjectDB.carList.size(); i++) {
            if (ProjectDB.carList.get(i).getCarID() == carID) {
                int CARID = ProjectDB.carList.get(i).getCarID();
                String MODEL = ProjectDB.carList.get(i).getModel();
                String BRAND = ProjectDB.carList.get(i).getBrand();
                String CAPACITY = String.valueOf(ProjectDB.carList.get(i).getCapacity());
                String TOPKPH = String.valueOf(ProjectDB.carList.get(i).getTopKPH());
                String TRANSMISSION = ProjectDB.carList.get(i).getTransmission();
                dispose();
                InputWindow newWindow = new InputWindow(CARID, MODEL, BRAND, CAPACITY, TOPKPH, TRANSMISSION);
                newWindow.setVisible(true);
                break;
            }
        }
    }
}