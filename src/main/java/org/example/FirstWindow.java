package org.example;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Comparator;

public class FirstWindow extends JFrame {
    private JButton buttonAdd;
    private JButton buttonEdit;
    private JButton buttonDelete;
    private JPanel mainPanel;
    private JScrollPane scrollPane;
    private JButton buttonSortByID;
    private JButton buttonSortByLargestCapacity;
    private JButton buttonSortByLeastCapacity;
    private JButton buttonSortByIDReversed;
    private JButton buttonRent;
    private JButton buttonReturn;
    private JButton buttonViewRented;


    public FirstWindow() {
        initComponents();
        ProjectDB.loadCars();
    }

    private void initComponents() {
        setTitle("Cars DashBoard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 500);
        setLocationRelativeTo(null);

        mainPanel = new JPanel(new BorderLayout());
        // Table setup
        String[] columnNames = {"CarsID", "Model", "Brand", "Capacity", "Top Speed", "isAutomatic"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        scrollPane = new JScrollPane(ProjectDB.getTable());

        // Buttons panel
        JPanel buttonPanel = new JPanel();
        buttonAdd = new JButton("Add New Car");
        buttonEdit = new JButton("Edit Car");
        buttonDelete = new JButton("Delete Car");
        buttonSortByID = new JButton("Sort by ID");
        buttonSortByIDReversed = new JButton("Sort by ID Reversed");
        buttonSortByLargestCapacity = new JButton("Sort by Largest Capacity");
        buttonSortByLeastCapacity = new JButton("Sort by Smallest Capacity");
        buttonRent = new JButton("Rent Car");
        buttonReturn = new JButton("Return Car");
        buttonViewRented = new JButton("View Rented Cars");


        buttonPanel.add(buttonAdd);
        buttonPanel.add(buttonEdit);
        buttonPanel.add(buttonDelete);
        buttonPanel.add(buttonSortByID);
        buttonPanel.add(buttonSortByIDReversed);
        buttonPanel.add(buttonSortByLargestCapacity);
        buttonPanel.add(buttonSortByLeastCapacity);
        buttonPanel.add(buttonRent);
        buttonPanel.add(buttonReturn);
        buttonPanel.add(buttonViewRented);


        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);

        pack();
        // buttons actions
        buttonAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InputWindow newWindow = new InputWindow();
                newWindow.setVisible(true);
            }
        });
        buttonEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EditCars newWindow = new EditCars();
                newWindow.setVisible(true);
            }
        });
        buttonDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DeleteCarWindow delete = new DeleteCarWindow();
                delete.setVisible(true);
            }
        });
        buttonSortByID.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProjectDB.sortCarsBy(Comparator.comparingInt(Car::getCarID));
                ProjectDB.loadCars();
            }
        });
        buttonSortByIDReversed.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProjectDB.sortCarsBy(Comparator.comparingInt(Car::getCarID).reversed());
                ProjectDB.loadCars();
            }
        });
        buttonSortByLargestCapacity.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProjectDB.sortCarsBy(Comparator.comparingInt(Car::getCapacity).reversed());
                ProjectDB.loadCars();
            }
        });
        buttonSortByLeastCapacity.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProjectDB.sortCarsBy(Comparator.comparingInt(Car::getCapacity));
                ProjectDB.loadCars();
            }
        });
        buttonRent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RentCarWindow rentWindow = new RentCarWindow();
                rentWindow.setVisible(true);
            }
        });

        buttonReturn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ReturnCarWindow returnWindow = new ReturnCarWindow();
                returnWindow.setVisible(true);
            }
        });

        buttonViewRented.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewRentedCarsWindow viewWindow = new ViewRentedCarsWindow();
                viewWindow.setVisible(true);
            }
        });
    }
}