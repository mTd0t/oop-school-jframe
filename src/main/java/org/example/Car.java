package org.example;

public class Car {
    private int carID;
    private String model;
    private String fuel;
    private int capacity;
    private float topKPH;
    private boolean isAutomatic;

    public Car(int carID, String model, String fuel, int capacity, float topKPH, boolean isAutomatic) {
        this.carID = carID;
        this.model = model;
        this.fuel = fuel;
        this.capacity = capacity;
        this.topKPH = topKPH;
        this.isAutomatic = isAutomatic;
    }
    // Getters and setters that match the actual fields
    public int getCarID() {
        return this.carID;
    }

    public void setCarID(int carID) {
        this.carID = carID;
    }

    public String getModel() {
        return this.model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getFuel() {
        return this.fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public int getCapacity() {
        return this.capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public float getTopKPH() {
        return this.topKPH;
    }

    public void setTopKPH(int topKPH) {
        this.topKPH = topKPH;
    }

    public boolean isAutomatic() {
        return this.isAutomatic;
    }

    public void setAutomatic(boolean isAutomatic) {
        this.isAutomatic = isAutomatic;
    }
}