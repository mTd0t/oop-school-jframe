package org.example;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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


        buttonPanel.add(buttonAdd);
        buttonPanel.add(buttonEdit);
        buttonPanel.add(buttonDelete);
        buttonPanel.add(buttonSortByID);
        buttonPanel.add(buttonSortByIDReversed);
        buttonPanel.add(buttonSortByLargestCapacity);
        buttonPanel.add(buttonSortByLeastCapacity);


        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);

        pack();
        // Button actions
        buttonAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InputWindow newWindow = new InputWindow();
                newWindow.setVisible(true);
                dispose();
            }
        });
        buttonEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EditCars newWindow = new EditCars();
                newWindow.setVisible(true);
                dispose();
            }
        });
        buttonDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DeleteCarWindow delete = new DeleteCarWindow();
                delete.setVisible(true);
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
        buttonSortByLargestCapacity.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProjectDB.sortCarsByLargestCapacity();
                ProjectDB.loadCars();
            }
        });
        buttonSortByLeastCapacity.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProjectDB.sortCarsBySmallestCapacity();
                ProjectDB.loadCars();
            }
        });
    }
}