package org.example;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Iterator;

// inheritance
public class ViewQueueWindow extends JFrame {
    // encapsulation
    private JTextField txtCarID;

    // constructor
    public ViewQueueWindow() {
        initComponents();
    }

    // encapsulation
    private void initComponents() {
        setTitle("View Rental Queue for Car");
        setSize(600, 400);
        setLocationRelativeTo(null);

        // composition
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // composition
        JPanel inputPanel = new JPanel(new FlowLayout());
        inputPanel.add(new JLabel("Car ID:"));
        txtCarID = new JTextField(10);
        inputPanel.add(txtCarID);

        // composition
        JButton buttonView = new JButton("View Queue");
        inputPanel.add(buttonView);

        // composition
        String[] columnNames = {"Name", "Phone", "Duration (days)"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        JTable queueTable = new JTable(model);

        mainPanel.add(inputPanel, BorderLayout.NORTH);
        mainPanel.add(new JScrollPane(queueTable), BorderLayout.CENTER);

        add(mainPanel);

        // observer pattern
        buttonView.addActionListener(e -> {
            int carID;
            try {
                carID = Integer.parseInt(txtCarID.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid Car ID!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            model.setRowCount(0);

            // iterator pattern
            Iterator<PendingRental> iterator = ProjectDB.pendingQueue.iterator();
            while (iterator.hasNext()) {
                PendingRental pending = iterator.next();
                if (pending.getCarID() == carID) {
                    model.addRow(new Object[]{
                            pending.getRenterName(),
                            pending.getRenterPhone(),
                            pending.getDurationDays()
                    });
                }
            }

            if (model.getRowCount() == 0) {
                JOptionPane.showMessageDialog(this, "No queued renters for this car.", "Info", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        pack();
    }
}