package org.example;

// encapsulation
public class PendingRental {
    // encapsulation
    private final String renterName;
    private final String renterPhone;
    private final int carID;
    private final int durationDays;

    // constructor
    public PendingRental(String renterName, String renterPhone, int carID, int durationDays) {
        this.renterName = renterName;
        this.renterPhone = renterPhone;
        this.carID = carID;
        this.durationDays = durationDays;
    }

    // encapsulation
    public String getRenterName() {
        return renterName;
    }

    // encapsulation
    public String getRenterPhone() {
        return renterPhone;
    }

    // encapsulation
    public int getCarID() {
        return carID;
    }

    // encapsulation
    public int getDurationDays() {
        return durationDays;
    }
}