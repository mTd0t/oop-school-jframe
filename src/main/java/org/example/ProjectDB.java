package org.example;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ProjectDB {
    private static final JTable table = new JTable(new DefaultTableModel(
            new Object[]{"ID", "Model", "Brand", "Capacity", "Top KPH", "Transmission", "Status", "Return Date"}, 0)) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    public static ArrayList<Car> carList;
    public static HashMap<Integer, RentalTransaction> activeRentals = new HashMap<>();
    public static LinkedList<PendingRental> pendingQueue = new LinkedList<>();

    static {
        // runns every hour to check and update expired rentals
        scheduler.scheduleAtFixedRate(ProjectDB::checkAndUpdateRentals, 0, 1, TimeUnit.HOURS);
    }

    public static void loadCars() {
        DefaultTableModel tm = (DefaultTableModel) table.getModel();
        tm.setRowCount(0);

        Object[] row = new Object[8];
        if (ProjectDB.carList != null) {
            for (Car e : ProjectDB.carList) {
                row[0] = e.getCarID();
                row[1] = e.getModel();
                row[2] = e.getBrand();
                row[3] = e.getCapacity();
                row[4] = e.getTopKPH();
                row[5] = e.getTransmission();

                // checks rental status
                if (e.isRented()) {
                    row[6] = "RENTED";
                    RentalTransaction rental = activeRentals.get(e.getCarID());
                    row[7] = (rental != null) ? rental.getFormattedReturnDate() : "N/A";
                } else {
                    row[6] = "AVAILABLE";
                    row[7] = "-";
                }
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

    public static void addExistingRentals() {
        activeRentals = new HashMap<>();
        activeRentals.put(5, new RentalTransaction("Santos Vince", "09765492925", 5, "Bronco Badlands", 1));
        activeRentals.put(3, new RentalTransaction("Rawr", "09565292925", 3, "Xpander", 3));
        activeRentals.put(8, new RentalTransaction("Pewpew", "1234567890", 8, "Telluride SX", 2));

        for (int carID : activeRentals.keySet()) {
            Car car = findCarByID(carID);
            if (car != null) {
                car.setRented(true);
            }
        }
    }

    // rent car function with a bit of validation
    public static boolean rentCar(int carID, String renterName, String renterPhone, int duration) {
        Car car = findCarByID(carID);
        if (car == null || car.isRented()) {
            return false;
        }

        car.setRented(true);
        RentalTransaction rental = new RentalTransaction(renterName, renterPhone, carID, car.getModel(), duration);
        activeRentals.put(carID, rental);
        loadCars();
        return true;
    }

    // return car function
    public static boolean returnCar(int carID) {
        RentalTransaction rental = activeRentals.get(carID);
        if (rental == null) {
            return false;
        }

        rental.setReturned(true);
        Car car = findCarByID(carID);
        if (car != null) {
            car.setRented(false);
        }
        activeRentals.remove(carID);

        // checks the queue for the car
        processPendingQueue(carID);
        loadCars();
        return true;
    }

    // adds to the queue if car is not available
    public static void addToPendingQueue(String renterName, String renterPhone, int carID, int duration) {
        pendingQueue.add(new PendingRental(renterName, renterPhone, carID, duration));
    }

    // processes the queue
    private static void processPendingQueue(int carID) {
        Iterator<PendingRental> iterator = pendingQueue.iterator();
        while (iterator.hasNext()) {
            PendingRental pending = iterator.next();
            if (pending.getCarID() == carID) {
                if (rentCar(carID, pending.getRenterName(), pending.getRenterPhone(), pending.getDurationDays())) {
                    JOptionPane.showMessageDialog(null,
                            "Car " + carID + " automatically rented to " + pending.getRenterName() + " from queue!",
                            "Queue Processed", JOptionPane.INFORMATION_MESSAGE);
                    iterator.remove();
                    break;
                }
            }
        }
    }

    // rental expiration checker
    private static void checkAndUpdateRentals() {
        LocalDate today = LocalDate.now();
        List<Integer> toRemove = new ArrayList<>();

        for (Map.Entry<Integer, RentalTransaction> entry : activeRentals.entrySet()) {
            if (entry.getValue().getReturnDate().isBefore(today) ||
                    entry.getValue().getReturnDate().isEqual(today)) {
                toRemove.add(entry.getKey());
            }
        }

        for (int carID : toRemove) {
            returnCar(carID); // auto returns expired rentals
        }
    }

    public static Car findCarByID(int carID) {
        for (Car car : carList) {
            if (car.getCarID() == carID) {
                return car;
            }
        }
        return null;
    }

    //gets all active rentals from the arrayList
    public static ArrayList<RentalTransaction> getAllActiveRentals() {
        return new ArrayList<>(activeRentals.values());
    }
}