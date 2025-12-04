package org.example;

public class PendingRental {
    private final String renterName;
    private final String renterPhone;
    private final int carID;
    private final int durationDays;

    public PendingRental(String renterName, String renterPhone, int carID, int durationDays) {
        this.renterName = renterName;
        this.renterPhone = renterPhone;
        this.carID = carID;
        this.durationDays = durationDays;
    }

    // getters
    public String getRenterName() {
        return renterName;
    }

    public String getRenterPhone() {
        return renterPhone;
    }

    public int getCarID() {
        return carID;
    }

    public int getDurationDays() {
        return durationDays;
    }
}