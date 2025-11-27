package org.example;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FirstWindow extends JFrame {
    private static JTable table;
    private JButton buttonAdd;
    private JButton buttonRefresh;
    private JButton buttonDelete;
    private JPanel mainPanel;
    private JScrollPane scrollPane;
    private JButton buttonSortByID;
    private JButton buttonSortByCapacity;
    private JButton buttonSortByCapacityReversed;
    private JButton buttonSortByIDReversed;


    public FirstWindow() {
        initComponents();
        loadCars();
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
        table = new JTable(model);
        scrollPane = new JScrollPane(table);

        // Buttons panel
        JPanel buttonPanel = new JPanel();
        buttonAdd = new JButton("Add New Movie");
        buttonRefresh = new JButton("Refresh");
        buttonDelete = new JButton("Delete Movie");
        buttonSortByID = new JButton("Sort by ID");
        buttonSortByIDReversed = new JButton("Sort by ID Reversed");
        buttonSortByCapacity = new JButton("Sort by Oldest");
        buttonSortByCapacityReversed = new JButton("Sort by Newest");


        buttonPanel.add(buttonAdd);
        buttonPanel.add(buttonRefresh);
        buttonPanel.add(buttonDelete);
        buttonPanel.add(buttonSortByID);
        buttonPanel.add(buttonSortByIDReversed);
        buttonPanel.add(buttonSortByCapacity);
        buttonPanel.add(buttonSortByCapacityReversed);


        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);

        // Button actions
        buttonAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InputWindow newWindow = new InputWindow();
                newWindow.setVisible(true);
                dispose();
            }
        });

        buttonRefresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadCars();
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
                loadCars();
            }
        });
        buttonSortByIDReversed.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProjectDB.sortCarsByIDReversed();
                loadCars();
            }
        });
        buttonSortByCapacity.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProjectDB.sortCarsByCapacity();
                loadCars();
            }
        });
        buttonSortByCapacityReversed.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProjectDB.sortCarsByCapacityReversed();
                loadCars();
            }
        });
    }

    public static void loadCars() {
        DefaultTableModel tm = (DefaultTableModel) table.getModel();
        tm.setRowCount(0);

        Object[] row = new Object[6];
        if (ProjectDB.carList != null) {
            for (Car e : ProjectDB.carList) {
                row[0] = e.getCarID();
                row[1] = e.getModel();
                row[2] = e.getFuel();
                row[3] = e.getCapacity();
                row[4] = e.getTopKPH();
                row[5] = e.isAutomatic();
                tm.addRow(row);
            }
        }
    }

}