package org.example;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// inheritance
public class DeleteCarWindow extends JFrame {
    // encapsulation
    private static JTable table;

    // constructor
    public DeleteCarWindow() {
        initComponents();
        ProjectDB.loadCars();
    }

    // encapsulation
    private void initComponents() {
        setTitle("Delete Car");
        setSize(900, 500);
        setLocationRelativeTo(null);

        // composition
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // composition
        String[] columnNames = {"CarsID", "Model", "Brand", "Capacity", "Top Speed", "isAutomatic"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        // composition
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton buttonDelete = new JButton("Delete Car");
        JButton buttonBack = new JButton("Cancel");

        JTextField txtCarID = new JTextField(15);
        buttonPanel.add(new JLabel("Car ID:"));
        buttonPanel.add(txtCarID);
        buttonPanel.add(buttonDelete);
        buttonPanel.add(buttonBack);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(mainPanel);

        pack();

        // observer pattern
        buttonDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int carID = Integer.parseInt(txtCarID.getText());
                deleteCar(carID);
                JOptionPane.showMessageDialog(DeleteCarWindow.this, "Car has been deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                dispose();
                ProjectDB.loadCars();
                ProjectDB.getTable().repaint();
            }
        });
        // observer pattern
        buttonBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                dispose();
            }
        });
    }

    // encapsulation
    public void deleteCar(int carID) {
        for (int i = 0; i < ProjectDB.carList.size(); i++) {
            if (ProjectDB.carList.get(i).getCarID() == carID) {
                ProjectDB.carList.remove(i);
                break;
            }
        }
    }
}