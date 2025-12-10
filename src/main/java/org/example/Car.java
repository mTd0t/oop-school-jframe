package org.example;

// encapsulation
public class Car {
    // encapsulation
    private int carID;
    private String model;
    private String brand;
    private int capacity;
    private int topKPH;
    private String transmission;
    private boolean rented = false;

    // constructor
    public Car(int carID, String model, String brand, int capacity, int topKPH, String transmission) {
        this.carID = carID;
        this.model = model;
        this.brand = brand;
        this.capacity = capacity;
        this.topKPH = topKPH;
        this.transmission = transmission;
    }

    // encapsulation
    public int getCarID() {
        return this.carID;
    }

    // encapsulation
    public void setCarID(int carID) {
        this.carID = carID;
    }

    // encapsulation
    public String getModel() {
        return this.model;
    }

    // encapsulation
    public void setModel(String model) {
        this.model = model;
    }

    // encapsulation
    public String getBrand() {
        return this.brand;
    }

    // encapsulation
    public void setBrand(String brand) {
        this.brand = brand;
    }

    // encapsulation
    public int getCapacity() {
        return this.capacity;
    }

    // encapsulation
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    // encapsulation
    public int getTopKPH() {
        return this.topKPH;
    }

    // encapsulation
    public void setTopKPH(int topKPH) {
        this.topKPH = topKPH;
    }

    // encapsulation
    public String getTransmission() {
        return this.transmission;
    }

    // encapsulation
    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    // encapsulation
    public boolean isRented() {
        return this.rented;
    }

    // encapsulation
    public void setRented(boolean rented) {
        this.rented = rented;
    }
}