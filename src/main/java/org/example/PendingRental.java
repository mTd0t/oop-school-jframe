package org.example;

public class PendingRental {
    private String renterName;
    private String renterPhone;
    private int carID;
    private int durationDays;

    public PendingRental(String renterName, String renterPhone, int carID, int durationDays) {
        this.renterName = renterName;
        this.renterPhone = renterPhone;
        this.carID = carID;
        this.durationDays = durationDays;
    }

    // getters
    public String getRenterName() { return renterName; }
    public String getRenterPhone() { return renterPhone; }
    public int getCarID() { return carID; }
    public int getDurationDays() { return durationDays; }
}