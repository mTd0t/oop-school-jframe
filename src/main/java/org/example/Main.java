package org.example;


public class Main {
    
    public static void main(String[] args) {
        ProjectDB.addExistingCars();
        ProjectDB.addExistingRentals();
        FirstWindow window = new FirstWindow();
        window.setVisible(true);
    }
}