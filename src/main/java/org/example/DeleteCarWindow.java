package org.example;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteCarWindow extends JFrame {
    private static JTable table;
    private JButton buttonDelete;
    private JPanel mainPanel;
    private JScrollPane scrollPane;
    private JButton buttonBack;
    private JButton buttonSortByID;
    private JButton buttonSortByOldest;
    private JButton buttonSortByNewest;
    private JButton buttonSortByIDReversed;


    public DeleteCarWindow() {
        initComponents();
        loadMovies();
    }

    private void initComponents() {
        setTitle("Movies DashBoard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 500);
        setLocationRelativeTo(null);

        // Use BorderLayout for the main panel instead of GridLayout
        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Table setup
        String[] columnNames = {"MovieID", "Title", "Genre", "Release Year"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);
        scrollPane = new JScrollPane(table);

        // Buttons panel with FlowLayout
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonDelete = new JButton("Delete Movie");
        buttonBack = new JButton("Back to Dashboard");
        buttonSortByID = new JButton("Sort by ID");
        buttonSortByIDReversed = new JButton("Sort by ID Reversed");
        buttonSortByOldest = new JButton("Sort by Oldest");
        buttonSortByNewest = new JButton("Sort by Newest");

        JTextField txtMovieID = new JTextField(15); // Set columns instead of size
        buttonPanel.add(new JLabel("Movie ID:"));
        buttonPanel.add(txtMovieID);
        buttonPanel.add(buttonDelete);
        buttonPanel.add(buttonBack);
        buttonPanel.add(buttonSortByID);
        buttonPanel.add(buttonSortByIDReversed);
        buttonPanel.add(buttonSortByOldest);
        buttonPanel.add(buttonSortByNewest);

        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(mainPanel);

        buttonDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteMovie(txtMovieID.getText());
                loadMovies();
            }
        });
        buttonBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                FirstWindow dash = new FirstWindow();
                dash.setVisible(true);
                dispose();
            }
        });buttonSortByID.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProjectDB.sortMoviesByID();
                loadMovies();
            }
        });
        buttonSortByIDReversed.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProjectDB.sortMoviesByIDReversed();
                loadMovies();
            }
        });
        buttonSortByOldest.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProjectDB.sortMoviesByOldest();
                loadMovies();
            }
        });
        buttonSortByNewest.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProjectDB.sortMoviesByNewest();
                loadMovies();
            }
        });

    }

    public void deleteMovie(String movieID) {
        for (int i = 0; i < ProjectDB.carList.size(); i++) {
            if (!ProjectDB.carList.get(i).equals(movieID)) {
                ProjectDB.carList.remove(i);
                break;
            }
        }
    }


    public static void loadMovies() {
        DefaultTableModel tm = (DefaultTableModel) table.getModel();
        tm.setRowCount(0);

        Object[] row = new Object[4];
        if (ProjectDB.carList != null) {
            for (Car e : ProjectDB.carList) {
                row[0] = e.getCarID();
                row[1] = e.getModel();
                row[2] = e.getBrand();
                row[3] = e.getCapacity();
                tm.addRow(row);
            }
        }
    }
}
