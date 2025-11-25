package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        FirstWindow window = new FirstWindow(); //good night sir
        ProjectDB.addExistingMovies();
        window.loadMovies();//refreshes the table, so you can see the added movies
        window.setVisible(true);
    }
}