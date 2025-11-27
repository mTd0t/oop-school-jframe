package org.example;

import java.util.ArrayList;
import java.util.Comparator;

public class ProjectDB {

    public static ArrayList<Car> carList = new ArrayList<Car>();

    public static void sortMoviesByID() {
        carList.sort(Comparator.comparingInt(Car::getCarID));
    }
    public static void sortMoviesByOldest() {
        carList.sort(Comparator.comparingInt(Car::getCapacity));
    }
    public static void sortMoviesByIDReversed() {
        carList.sort(Comparator.comparingInt(Car::getCarID));
        carList = new ArrayList<>(carList.reversed());
    }

    public static void sortMoviesByNewest() {
        carList.sort(Comparator.comparingInt(Car::getCapacity));
        carList = new ArrayList<>(carList.reversed());
    }
    public static void addExistingMovies(){
        Car newCar = new Car(2, "Pirates of the Caribbean", "Action, Thriller, Comedy", 2003);
        ProjectDB.carList.add(newCar);
        Car newCar2 = new Car(3, "Jurassic Park", "Action, Thriller, Horror", 1993);
        ProjectDB.carList.add(newCar2);
        Car newCar3 = new Car(4, "Frozen", "Drama, Comedy", 2013);
        ProjectDB.carList.add(newCar3);

    }
}