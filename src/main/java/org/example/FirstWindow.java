package org.example;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Comparator;

// inheritance
public class FirstWindow extends JFrame {
    // encapsulation
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
    private JButton buttonViewQueue;

    // constructor
    public FirstWindow() {
        initComponents();
        ProjectDB.loadCars();
    }

    // encapsulation
    private void initComponents() {
        setTitle("Cars DashBoard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // composition
        mainPanel = new JPanel(new BorderLayout());
        String[] columnNames = {"CarsID", "Model", "Brand", "Capacity", "Top Speed", "isAutomatic"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        scrollPane = new JScrollPane(ProjectDB.getTable());

        // composition
        JPanel sortButtonPanel = new JPanel();
        JPanel functionalButtonPanel = new JPanel(new GridLayout(0,1));

        buttonViewRented = new JButton("View Rented Cars");
        buttonAdd = new JButton("Add New Car");
        buttonEdit = new JButton("Edit Car");
        buttonDelete = new JButton("Delete Car");
        buttonRent = new JButton("Rent Car");
        buttonReturn = new JButton("Return Car");
        buttonViewQueue = new JButton("View Queue");

        buttonSortByID = new JButton("Sort by ID");
        buttonSortByIDReversed = new JButton("Sort by ID Reversed");
        buttonSortByLargestCapacity = new JButton("Sort by Largest Capacity");
        buttonSortByLeastCapacity = new JButton("Sort by Smallest Capacity");

        sortButtonPanel.add(buttonSortByID);
        sortButtonPanel.add(buttonSortByIDReversed);
        sortButtonPanel.add(buttonSortByLargestCapacity);
        sortButtonPanel.add(buttonSortByLeastCapacity);

        functionalButtonPanel.add(buttonAdd);
        functionalButtonPanel.add(buttonEdit);
        functionalButtonPanel.add(buttonDelete);
        functionalButtonPanel.add(buttonRent);
        functionalButtonPanel.add(buttonReturn);
        functionalButtonPanel.add(buttonViewRented);
        functionalButtonPanel.add(buttonViewQueue);

        mainPanel.setPreferredSize(new Dimension(900, 500));
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(sortButtonPanel, BorderLayout.SOUTH);
        mainPanel.add(functionalButtonPanel, BorderLayout.WEST);

        add(mainPanel);

        pack();

        // observer pattern
        buttonAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InputWindow newWindow = new InputWindow();
                newWindow.setVisible(true);
            }
        });
        // observer pattern
        buttonEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EditCars newWindow = new EditCars();
                newWindow.setVisible(true);
            }
        });
        // observer pattern
        buttonDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DeleteCarWindow delete = new DeleteCarWindow();
                delete.setVisible(true);
            }
        });
        // observer pattern
        buttonSortByID.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProjectDB.sortCarsBy(Comparator.comparingInt(Car::getCarID));
                ProjectDB.loadCars();
            }
        });
        // observer pattern
        buttonSortByIDReversed.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProjectDB.sortCarsBy(Comparator.comparingInt(Car::getCarID).reversed());
                ProjectDB.loadCars();
            }
        });
        // observer pattern
        buttonSortByLargestCapacity.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProjectDB.sortCarsBy(Comparator.comparingInt(Car::getCapacity).reversed());
                ProjectDB.loadCars();
            }
        });
        // observer pattern
        buttonSortByLeastCapacity.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProjectDB.sortCarsBy(Comparator.comparingInt(Car::getCapacity));
                ProjectDB.loadCars();
            }
        });
        // observer pattern
        buttonRent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RentCarWindow rentWindow = new RentCarWindow();
                rentWindow.setVisible(true);
            }
        });
        // observer pattern
        buttonReturn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ReturnCarWindow returnWindow = new ReturnCarWindow();
                returnWindow.setVisible(true);
            }
        });
        // observer pattern
        buttonViewRented.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewRentedCarsWindow viewWindow = new ViewRentedCarsWindow();
                viewWindow.setVisible(true);
            }
        });
        // observer pattern
        buttonViewQueue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewQueueWindow queueWindow = new ViewQueueWindow();
                queueWindow.setVisible(true);
            }
        });
    }
}