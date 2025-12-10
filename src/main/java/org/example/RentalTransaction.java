package org.example;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

// encapsulation
public class RentalTransaction {
    // encapsulation
    private final String renterName;
    private final String renterPhone;
    private final int carID;
    private final String carModel;
    private final LocalDate rentDate;
    private final int durationDays;
    private final LocalDate returnDate;
    private boolean isReturned;

    // constructor
    public RentalTransaction(String renterName, String renterPhone, int carID, String carModel, int durationDays) {
        this.renterName = renterName;
        this.renterPhone = renterPhone;
        this.carID = carID;
        this.carModel = carModel;
        this.rentDate = LocalDate.now();
        this.durationDays = durationDays;
        this.returnDate = rentDate.plusDays(durationDays);
        this.isReturned = false;
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
    public String getCarModel() {
        return carModel;
    }

    // encapsulation
    public LocalDate getRentDate() {
        return rentDate;
    }

    // encapsulation
    public int getDurationDays() {
        return durationDays;
    }

    // encapsulation
    public LocalDate getReturnDate() {
        return returnDate;
    }

    // encapsulation
    public boolean isReturned() {
        return isReturned;
    }

    // encapsulation
    public void setReturned(boolean returned) {
        isReturned = returned;
    }

    // encapsulation
    public String getFormattedReturnDate() {
        return returnDate.format(DateTimeFormatter.ofPattern("MMM dd, yyyy"));
    }
}