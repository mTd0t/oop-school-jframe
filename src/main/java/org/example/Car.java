package org.example;

public class Car {
    private int carID;
    private String model;
    private String brand;
    private int capacity;
    private int topKPH;
    private String transmission;

    public Car(int carID, String model, String brand, int capacity, int topKPH, String transmission) {
        this.carID = carID;
        this.model = model;
        this.brand = brand;
        this.capacity = capacity;
        this.topKPH = topKPH;
        this.transmission = transmission;
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

    public String getBrand() {
        return this.brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getCapacity() {
        return this.capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getTopKPH() {
        return this.topKPH;
    }

    public void setTopKPH(int topKPH) {
        this.topKPH = topKPH;
    }

    public String getTransmission() {
        return this.transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }
}