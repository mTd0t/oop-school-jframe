package org.example;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

// inheritance
public class ViewRentedCarsWindow extends JFrame {
    // constructor
    public ViewRentedCarsWindow() {
        initComponents();
    }

    // encapsulation
    private void initComponents() {
        setTitle("Currently Rented Cars");
        setLocationRelativeTo(null);

        // composition
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.setPreferredSize(new Dimension(600, 400));

        // composition
        String[] columnNames = {"Car ID", "Model", "Renter Name", "Phone", "Rent Date", "Duration", "Return Date"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        JTable rentedTable = new JTable(model);

        // load rented cars data
        // composition
        ArrayList<RentalTransaction> rentals = ProjectDB.getAllActiveRentals();
        for (RentalTransaction rental : rentals) {
            model.addRow(new Object[]{
                    rental.getCarID(),
                    rental.getCarModel(),
                    rental.getRenterName(),
                    rental.getRenterPhone(),
                    rental.getRentDate().format(DateTimeFormatter.ofPattern("MMM dd, yyyy")),
                    rental.getDurationDays() + " days",
                    rental.getFormattedReturnDate()
            });
        }

        mainPanel.add(new JScrollPane(rentedTable), BorderLayout.CENTER);
        add(mainPanel);
        pack();
    }
}