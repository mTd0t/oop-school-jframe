package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InputWindow extends JFrame {
    private final JTextField txtCarID;
    private final JTextField txtModel;
    private final JTextField txtBrand;
    private final JTextField txtCapacity;
    private final JTextField txtTopKPH;
    private final JRadioButton option1;
    private final JRadioButton option2;
    private final ButtonGroup transmissionButtonGroup;


    private final JButton btnSave;
    private final JButton btnBack;
    private final int i = 0;
    private boolean isValid = true;
    private int oldCarID;

    public InputWindow() {
        setTitle("Add New Car");
        setSize(900, 500);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new GridLayout(7, 1, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));


        mainPanel.add(new JLabel("Car ID: "));
        txtCarID = new JTextField();
        mainPanel.add(txtCarID);

        mainPanel.add(new JLabel("Model: "));
        txtModel = new JTextField();
        mainPanel.add(txtModel);

        mainPanel.add(new JLabel("Brand: "));
        txtBrand = new JTextField();
        mainPanel.add(txtBrand);

        mainPanel.add(new JLabel("Capacity: "));
        txtCapacity = new JTextField();
        mainPanel.add(txtCapacity);

        mainPanel.add(new JLabel("TopKPH: "));
        txtTopKPH = new JTextField();
        mainPanel.add(txtTopKPH);

        //radio options for auto or manualk
        option1 = new JRadioButton("Automatic");
        option2 = new JRadioButton("Manual");
        transmissionButtonGroup = new ButtonGroup();
        transmissionButtonGroup.add(option1);
        transmissionButtonGroup.add(option2);

        JPanel radioPanel = new JPanel(new FlowLayout());
        radioPanel.add(option1);
        radioPanel.add(option2);

        mainPanel.add(new JLabel("Transmission:"));
        mainPanel.add(radioPanel);

        //end of radio options

        btnSave = new JButton("Save Car");
        btnBack = new JButton("Cancel");

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(btnSave);
        buttonPanel.add(btnBack);

        add(mainPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        pack();

        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                saveCar();
                dispose();
                ProjectDB.loadCars();
            }
        });

        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                dispose();
            }
        });
    }

    //constructor Chaining
    public InputWindow(int carID, String model, String brand, String capacity, String topKPH, String transmission) {
        setTitle("Edit Car");
        setSize(900, 500);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new GridLayout(7, 1, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        oldCarID = carID;

        mainPanel.add(new JLabel("Car ID: "));
        txtCarID = new JTextField(String.valueOf(carID));
        txtCarID.setEditable(false);
        mainPanel.add(txtCarID);

        mainPanel.add(new JLabel("Model: "));
        txtModel = new JTextField(model);
        mainPanel.add(txtModel);

        mainPanel.add(new JLabel("Brand: "));
        txtBrand = new JTextField(brand);
        mainPanel.add(txtBrand);

        mainPanel.add(new JLabel("Capacity: "));
        txtCapacity = new JTextField(capacity);
        mainPanel.add(txtCapacity);

        mainPanel.add(new JLabel("TopKPH: "));
        txtTopKPH = new JTextField(topKPH);
        mainPanel.add(txtTopKPH);

        // radio options
        option1 = new JRadioButton("Automatic");
        option2 = new JRadioButton("Manual");

        if (transmission.equals("Automatic")) {
            option1.setSelected(true);
        } else if (transmission.equals("Manual")) {
            option2.setSelected(true);
        }

        transmissionButtonGroup = new ButtonGroup();
        transmissionButtonGroup.add(option1);
        transmissionButtonGroup.add(option2);

        JPanel radioPanel = new JPanel(new FlowLayout());
        radioPanel.add(option1);
        radioPanel.add(option2);

        mainPanel.add(new JLabel("Transmission:"));
        mainPanel.add(radioPanel);

        btnSave = new JButton("Save Edited Car");
        btnBack = new JButton("Cancel");

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(btnSave);
        buttonPanel.add(btnBack);

        add(mainPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        pack();

        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                saveEdit();
                dispose();
                ProjectDB.loadCars();
            }
        });

        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                dispose();
            }
        });
    }

    private void saveEdit() {
        try {
            isValid = true;
            int newCarID = Integer.parseInt(txtCarID.getText());
            String newModel = txtModel.getText();
            String newBrand = txtBrand.getText();
            int newCapacity = Integer.parseInt(txtCapacity.getText());
            int newTopKPH = Integer.parseInt(txtTopKPH.getText());
            String newTransmission = getSelectedTransmission();


            // checks if model and brand is filled in
            if (newModel.isEmpty() || newBrand.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all fields", "ERROR", JOptionPane.ERROR_MESSAGE);
                isValid = false;
            }

            //checks if the capacity is valid
            if (newCapacity < 0 || newCapacity > 300) {
                JOptionPane.showMessageDialog(this, "Enter valid capacity \n 0-300", "ERROR", JOptionPane.ERROR_MESSAGE);
                isValid = false;
            }

            //checks if user selected a transmission type
            if (!option1.isSelected() && !option2.isSelected()) {
                isValid = false;
            }

            // find index of car
            int editIndex = -1;
            for (int i = 0; i < ProjectDB.carList.size(); i++) {
                if (ProjectDB.carList.get(i).getCarID() == oldCarID) {
                    editIndex = i;
                    break;
                }
            }

            // check for duplicate id
            if (newCarID != oldCarID) {
                for (Car car : ProjectDB.carList) {
                    if (car.getCarID() == newCarID) {
                        JOptionPane.showMessageDialog(this, "Car ID already exists!", "ERROR", JOptionPane.ERROR_MESSAGE);
                        isValid = false;
                        break;
                    }
                }
            }
            if (isValid && editIndex != -1) {
                ProjectDB.carList.remove(editIndex);
                Car newCar = new Car(newCarID, newModel, newBrand, newCapacity, newTopKPH, newTransmission);
                ProjectDB.carList.add(editIndex, newCar);

                JOptionPane.showMessageDialog(this, "Car edited successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

                // clears everything on the form
                clearForm();

                System.out.println("Total cars: " + ProjectDB.carList.size());
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter valid Integers for ID, Capacity and Top KPH", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void saveCar() {
        try {
            isValid = true;
            int carID = Integer.parseInt(txtCarID.getText());
            String model = txtModel.getText();
            String brand = txtBrand.getText();
            int capacity = Integer.parseInt(txtCapacity.getText());
            int topKPH = Integer.parseInt(txtTopKPH.getText());
            String transmission = getSelectedTransmission();


            // checks if model and brand is filled in
            if (model.isEmpty() || brand.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all fields", "ERROR", JOptionPane.ERROR_MESSAGE);
                isValid = false;
            }

            //checks if the capacity is valid
            if (capacity < 0 || capacity > 300) {
                JOptionPane.showMessageDialog(this, "Enter valid capacity \n 0-300", "ERROR", JOptionPane.ERROR_MESSAGE);
                isValid = false;
            }

            //checks if user selected a transmission type
            if (!option1.isSelected() && !option2.isSelected()) {
                JOptionPane.showMessageDialog(this, "Select a Transmission type!", "ERROR", JOptionPane.ERROR_MESSAGE);
                isValid = false;
            }

            //checks if the carID is already in use
            for (int i = 0; i < ProjectDB.carList.size(); i++) {
                if (carID == ProjectDB.carList.get(i).getCarID()) {
                    JOptionPane.showMessageDialog(this, "Car ID already exists!", "ERROR", JOptionPane.ERROR_MESSAGE);
                    isValid = false;
                }
            }
            if (isValid) {

                Car newCar = new Car(carID, model, brand, capacity, topKPH, transmission);
                ProjectDB.carList.add(newCar);

                JOptionPane.showMessageDialog(this, "Car added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

                // clear entire form
                clearForm();

                System.out.println("Total cars: " + ProjectDB.carList.size());
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter valid Integers for ID, Capacity and Top KPH", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private String getSelectedTransmission() {
        if (option1.isSelected()) {
            return "Automatic";
        } else {
            return "Manual";
        }
    }

    private void clearForm() {
        txtCarID.setText("");
        txtModel.setText("");
        txtBrand.setText("");
        txtCapacity.setText("");
        txtTopKPH.setText("");
        option1.setSelected(false);
        option2.setSelected(false);
        System.out.println("Form cleared");
    }
}