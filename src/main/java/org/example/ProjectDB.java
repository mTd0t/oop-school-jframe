package org.example;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.Comparator;


public class ProjectDB {
    private static final JTable table = new JTable(new DefaultTableModel(new Object[]{"ID", "Model", "Brand", "Capacity", "Top KPH", "Automatic"}, 0));
    public static ArrayList<Car> carList = new ArrayList<Car>();

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

    public static void sortCarsByID() {
        carList.sort(Comparator.comparingInt(Car::getCarID));
    }

    public static void sortCarsByLargestCapacity() {
        carList.sort(Comparator.comparingInt(Car::getCapacity));
        carList = new ArrayList<>(carList.reversed());
    }

    public static void sortCarsByIDReversed() {
        carList.sort(Comparator.comparingInt(Car::getCarID));
        carList = new ArrayList<>(carList.reversed());
    }

    public static void sortCarsBySmallestCapacity() {
        carList.sort(Comparator.comparingInt(Car::getCapacity));
    }

    public static void addExistingCars() {
        Car newCar = new Car(1, "Adventure", "Mitsubishi", 7, 140, "Manual");
        ProjectDB.carList.add(newCar);
        Car newCar2 = new Car(2, "Aventador", "Lamborghini", 2, 350, "Automatic");
        ProjectDB.carList.add(newCar2);
        Car newCar3 = new Car(3, "Xpander", "Mitsubishi", 7, 170, "Automatic");
        ProjectDB.carList.add(newCar3);
        Car newCar4 = new Car(4, "Civic Type R", "Honda", 5, 275, "Manual");
        ProjectDB.carList.add(newCar4);
        Car newCar5 = new Car(5, "Bronco Badlands", "Ford", 5, 180, "Manual");
        ProjectDB.carList.add(newCar5);
        Car newCar6 = new Car(6, "WRX GT", "Subaru", 5, 60, "Automatic");
        ProjectDB.carList.add(newCar6);
        Car newCar7 = new Car(7, "ES350", "Lexus", 5, 210, "Automatic");
        ProjectDB.carList.add(newCar7);
        Car newCar8 = new Car(8, "Telluride SX", "Kia", 8, 190, "Automatic");
        ProjectDB.carList.add(newCar8);
        Car newCar9 = new Car(9, "Corvette Stingray", "Chevrolet", 2, 290, "Automatic");
        ProjectDB.carList.add(newCar9);
    }
}