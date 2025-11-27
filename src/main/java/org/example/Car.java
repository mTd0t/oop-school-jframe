package org.example;

public class Car {
    private int carID;
    private String model;
    private String brand;
    private int capacity;

    public Car(int carID, String model, String brand, int capacity) {
        this.carID = carID;
        this.model = model;
        this.brand = brand;
        this.capacity = capacity;
    }
    // Getters and setters that match the actual fields
    public int getCarID() {
        return carID;
    }

    public void setCarID(int carID) {
        this.carID = carID;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}