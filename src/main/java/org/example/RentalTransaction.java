package org.example;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class RentalTransaction {
    private final String renterName;
    private final String renterPhone;
    private final int carID;
    private final String carModel;
    private final LocalDate rentDate;
    private final int durationDays;
    private final LocalDate returnDate;
    private boolean isReturned;

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

    public String getRenterName() {
        return renterName;
    }

    public String getRenterPhone() {
        return renterPhone;
    }

    public int getCarID() {
        return carID;
    }

    public String getCarModel() {
        return carModel;
    }

    public LocalDate getRentDate() {
        return rentDate;
    }

    public int getDurationDays() {
        return durationDays;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public boolean isReturned() {
        return isReturned;
    }

    public void setReturned(boolean returned) {
        isReturned = returned;
    }

    public String getFormattedReturnDate() {
        return returnDate.format(DateTimeFormatter.ofPattern("MMM dd, yyyy"));
    }
}