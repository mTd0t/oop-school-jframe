package org.example;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class ProjectDB {
    private static final JTable table = new JTable(new DefaultTableModel(new Object[]{"ID", "Model", "Brand", "Capacity", "Top KPH", "Automatic"}, 0));
    public static ArrayList<Car> carList;

    public static void loadCars() {
        DefaultTableModel tm = (DefaultTableModel) table.getModel();
        tm.setRowCount(0);

        Object[] row = new Object[6];
        if (ProjectDB.carList != null) {
            for (Car e : ProjectDB.carList) {
                row[0] = e.getCarID();
                row[1] = e.getModel();
                row[2] = e.getBrand();
                row[3] = e.getCapacity();
                row[4] = e.getTopKPH();
                row[5] = e.getTransmission();
                tm.addRow(row);
            }
        }
    }

    public static JComponent getTable() {
        return table;
    }

    public static void sortCarsBy(Comparator<Car> comparator) {
        carList.sort(comparator);
    }

    public static void addExistingCars() {
        carList = new ArrayList<Car>(List.of(
                new Car(1, "Adventure", "Mitsubishi", 7, 140, "Manual"),
                new Car(2, "Aventador", "Lamborghini", 2, 350, "Automatic"),
                new Car(3, "Xpander", "Mitsubishi", 7, 170, "Automatic"),
                new Car(4, "Civic Type R", "Honda", 5, 275, "Manual"),
                new Car(5, "Bronco Badlands", "Ford", 5, 180, "Manual"),
                new Car(6, "WRX GT", "Subaru", 5, 60, "Automatic"),
                new Car(7, "ES350", "Lexus", 5, 210, "Automatic"),
                new Car(8, "Telluride SX", "Kia", 8, 190, "Automatic"),
                new Car(9, "Corvette Stingray", "Chevrolet", 2, 290, "Automatic")
        ));
    }
}