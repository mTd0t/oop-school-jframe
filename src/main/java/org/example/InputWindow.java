package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InputWindow extends JFrame {
    private JTextField txtCarID;
    private JTextField txtModel;
    private JTextField txtBrand;
    private JTextField txtCapacity;
    private JTextField txtTopKPH;
    private JRadioButton option1, option2;
    private ButtonGroup isAutomaticGroup;


    private JButton btnSave;
    private JButton btnBack;
    private boolean isValid = true;

    public InputWindow() {
        initComponents();
    }

    private void initComponents() {
        setTitle("Add New Car");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new GridLayout(5, 2, 10, 10));
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

        //Radio options for Automatic or Manual
        option1 = new JRadioButton("Automatic");
        option2 = new JRadioButton("Manual");
        isAutomaticGroup = new ButtonGroup();
        isAutomaticGroup.add(option1);
        isAutomaticGroup.add(option2);
        //end of Radio options

        btnSave = new JButton("Save Car");
        btnBack = new JButton("Back to Dashboard");

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(btnSave);
        buttonPanel.add(btnBack);

        add(mainPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                saveMovie();
            }
        });

        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                FirstWindow dsh = new FirstWindow();
                dsh.setVisible(true);
                dispose();
            }
        });
    }

    private void saveMovie() {
        try {
            int carID = Integer.parseInt(txtCarID.getText());
            String model = txtModel.getText();
            String brand = txtBrand.getText();
            int capacity = Integer.parseInt(txtCapacity.getText());
            int topKPH = Integer.parseInt(txtTopKPH.getText());
            String trueOrFalse;
            boolean isAutomatic;

            //changing isAutomaticGroup String to true or false, true for automatic and false for manual
            if(isAutomaticGroup.toString().contains("Automatic")){
                isAutomatic = true;
            }else{
                isAutomatic = false;
            }

            // Basic validation
            if (model.isEmpty() || brand.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all fields", "ERROR", JOptionPane.ERROR_MESSAGE);
                isValid = false;
            }
            if (capacity < 0 || capacity > 300) {
                JOptionPane.showMessageDialog(this, "Enter valid capacity \n 0-300", "ERROR", JOptionPane.ERROR_MESSAGE);
                isValid = false;
            }

            for (int i = 0; i < ProjectDB.carList.size(); i++) {
                if (carID == ProjectDB.carList.get(i).getCarID()) {
                    JOptionPane.showMessageDialog(this, "Movie ID already exists!", "ERROR", JOptionPane.ERROR_MESSAGE);
                    isValid = false;
                }
            }
            if(isValid){

                Car newCar = new Car(carID, model, brand, capacity, topKPH, isAutomatic);
                ProjectDB.carList.add(newCar);

                JOptionPane.showMessageDialog(this, "Movie added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

                // Clear all fields including brand checkboxes
                clearForm();

                System.out.println("Total movies: " + ProjectDB.carList.size());
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers for ID and Year", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearForm() {
        txtCarID.setText("");
        txtModel.setText("");
        txtBrand.setText("");
        txtCapacity.setText("");
        txtTopKPH.setText("");

        System.out.println("Form cleared");
    }
}