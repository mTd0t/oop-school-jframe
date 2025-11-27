package org.example;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditCars extends JFrame {
    private static JTable table;
    private JButton buttonEdit;
    private JPanel mainPanel;
    private JScrollPane scrollPane;
    private JButton buttonBack;
    private JButton buttonSortByID;
    private JButton buttonSortByIDReversed;
    private JButton buttonSortByCapacity;
    private JButton buttonSortByCapacityReverse;


    public EditCars() {
        initComponents();
        ProjectDB.loadCars();
    }

    private void initComponents() {
        setTitle("Movies DashBoard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 500);
        setLocationRelativeTo(null);

        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Table setup
        String[] columnNames = {"CarsID", "Model", "Brand", "Capacity", "Top Speed", "isAutomatic"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        scrollPane = new JScrollPane(ProjectDB.getTable());

        // Buttons panel with FlowLayout
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonEdit = new JButton("Edit Car");
        buttonBack = new JButton("Back to Dashboard");
        buttonSortByID = new JButton("Sort by ID");
        buttonSortByIDReversed = new JButton("Sort by ID Reversed");
        buttonSortByCapacity = new JButton("Sort by Largest Capacity");
        buttonSortByCapacityReverse = new JButton("Sort by Smallest Capacity");

        JTextField txtCarID = new JTextField(15); // Set columns instead of size
        buttonPanel.add(new JLabel("Car ID:"));
        buttonPanel.add(txtCarID);
        buttonPanel.add(buttonEdit);
        buttonPanel.add(buttonBack);
        buttonPanel.add(buttonSortByID);
        buttonPanel.add(buttonSortByIDReversed);
        buttonPanel.add(buttonSortByCapacity);
        buttonPanel.add(buttonSortByCapacityReverse);

        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(mainPanel);

        pack();

        buttonEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int carID = Integer.parseInt(txtCarID.getText());
                editMovie(carID);
                ProjectDB.loadCars();
            }
        });
        buttonBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                FirstWindow dash = new FirstWindow();
                dash.setVisible(true);
                dispose();
            }
        });
        buttonSortByID.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProjectDB.sortCarsByID();
                ProjectDB.loadCars();
            }
        });
        buttonSortByIDReversed.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProjectDB.sortCarsByIDReversed();
                ProjectDB.loadCars();
            }
        });
        buttonSortByCapacity.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProjectDB.sortCarsByLargestCapacity();
                ProjectDB.loadCars();
            }
        });
        buttonSortByCapacityReverse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProjectDB.sortCarsBySmallestCapacity();
                ProjectDB.loadCars();
            }
        });
    }

    public void editMovie(int carID) {
        for (int i = 0; i < ProjectDB.carList.size(); i++) {
            if (ProjectDB.carList.get(i).getCarID() == carID) {
                ProjectDB.carList.remove(i);
                break;
            }
        }
    }
}
