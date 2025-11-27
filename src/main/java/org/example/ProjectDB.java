package org.example;

import java.util.ArrayList;
import java.util.Comparator;

public class ProjectDB {

    public static ArrayList<Car> carList = new ArrayList<Car>();

    public static void sortCarsByID() {
        carList.sort(Comparator.comparingInt(Car::getCarID));
    }

    public static void sortCarsByCapacity() {
        carList.sort(Comparator.comparingInt(Car::getCapacity));
    }

    public static void sortCarsByIDReversed() {
        carList.sort(Comparator.comparingInt(Car::getCarID));
        carList = new ArrayList<>(carList.reversed());
    }

    public static void sortCarsByCapacityReversed() {
        carList.sort(Comparator.comparingInt(Car::getCapacity));
        carList = new ArrayList<>(carList.reversed());
    }

    public static void addExistingCars() {
        Car newCar = new Car(1, "Adventure", "Mitsubishi", 7, 140, false);
        ProjectDB.carList.add(newCar);
        Car newCar2 = new Car(2, "Aventador", "Mitsubishi", 7, 140, false);
        ProjectDB.carList.add(newCar);
        Car newCar3 = new Car(3, "Adventure", "Mitsubishi", 7, 140, false);
        ProjectDB.carList.add(newCar);
        Car newCar4 = new Car(4, "Civic Type R", "Honda", 5, 275, false);
        ProjectDB.carList.add(newCar4);
        Car newCar5 = new Car(5, "Bronco Badlands", "Ford", 5, 180, false);
        ProjectDB.carList.add(newCar5);
        Car newCar6 = new Car(6, "WRX GT", "Subaru", 5, 60, false);
        ProjectDB.carList.add(newCar6);
        Car newCar7 = new Car(7, "ES350", "Lexus", 5, 210, true);
        ProjectDB.carList.add(newCar7);
        Car newCar8 = new Car(8, "Telluride SX", "Kia", 8, 190, true);
        ProjectDB.carList.add(newCar8);
        Car newCar9 = new Car(9, "Corvette Stingray", "Chevrolet", 2, 290, true);
        ProjectDB.carList.add(newCar9);
    }
}