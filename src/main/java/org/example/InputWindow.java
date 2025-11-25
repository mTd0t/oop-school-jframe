package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class InputWindow extends JFrame {
    private JTextField txtMovieID;
    private JTextField txtTitle;
    private String txtGenre;
    private JTextField txtReleaseYear;
    private JButton btnSave;
    private JButton btnBack;
    public String[] genres = {"Action", "Comedy", "Drama", "Horror", "Sci-Fi", "Romance", "Thriller"};
    private List<JCheckBox> genreCheckBoxes;
    private List<String> selectedGenres;
    private JPanel genrePanel;
    private boolean isValid = true;

    public InputWindow() {
        initComponents();
    }

    private void initComponents() {
        setTitle("Add New Movie");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        mainPanel.add(new JLabel("Movie ID:"));
        txtMovieID = new JTextField();
        mainPanel.add(txtMovieID);

        mainPanel.add(new JLabel("Title:"));
        txtTitle = new JTextField();
        mainPanel.add(txtTitle);

        mainPanel.add(new JLabel("Genre:"));
        genrePanel = new JPanel(new GridLayout(0, 2));
        genreCheckBoxes = new ArrayList<>();
        selectedGenres = new ArrayList<>();

        for (String genre : genres) {
            JCheckBox checkBox = new JCheckBox(genre);
            genrePanel.add(checkBox);
            genreCheckBoxes.add(checkBox);

            checkBox.addActionListener(e -> {
                if (checkBox.isSelected()) {
                    selectedGenres.add(genre);
                } else {
                    selectedGenres.remove(genre);
                }

                this.txtGenre = String.join(", ", selectedGenres);
                System.out.println("Selected genres: " + this.txtGenre);
            });
        }
        mainPanel.add(genrePanel);

        mainPanel.add(new JLabel("Release Year:"));
        txtReleaseYear = new JTextField();
        mainPanel.add(txtReleaseYear);

        btnSave = new JButton("Save Movie");
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
            int movieID = Integer.parseInt(txtMovieID.getText());
            String title = txtTitle.getText();
            String genre = txtGenre;
            int releaseYear = Integer.parseInt(txtReleaseYear.getText());

            // Basic validation
            if (title.isEmpty() || genre.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all fields", "ERROR", JOptionPane.ERROR_MESSAGE);
                isValid = false;
            }
            if (releaseYear < 1878 || releaseYear > 2026) {
                JOptionPane.showMessageDialog(this, "Enter valid release year \n 1878-2026", "ERROR", JOptionPane.ERROR_MESSAGE);
                isValid = false;
            }

            for(int i = 0; i < ProjectDB.movieList.size(); i++) {
                if(movieID == ProjectDB.movieList.get(i).getMovieID()) {
                    JOptionPane.showMessageDialog(this, "Movie ID already exists!", "ERROR", JOptionPane.ERROR_MESSAGE);
                    isValid = false;
                }
            }
            if(!isValid){
                return;
            }else{

            Movie newMovie = new Movie(movieID, title, genre, releaseYear);
            ProjectDB.movieList.add(newMovie);

            JOptionPane.showMessageDialog(this, "Movie added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

            // Clear all fields including genre checkboxes
            clearForm();

            System.out.println("Total movies: " + ProjectDB.movieList.size());}

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers for ID and Year", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearForm() {
        txtMovieID.setText("");
        txtTitle.setText("");
        txtReleaseYear.setText("");

        // Clear all genre checkboxes
        for (JCheckBox checkBox : genreCheckBoxes) {
            checkBox.setSelected(false);
        }
        selectedGenres.clear();
        txtGenre = "";

        System.out.println("Form cleared");
    }
}